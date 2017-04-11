package com.pattern.generator.runner;

import iot.jcypher.database.DBAccessFactory;
import iot.jcypher.database.DBProperties;
import iot.jcypher.database.DBType;
import iot.jcypher.database.IDBAccess;
import iot.jcypher.graph.GrLabel;
import iot.jcypher.graph.GrNode;
import iot.jcypher.graph.GrProperty;
import iot.jcypher.query.JcQuery;
import iot.jcypher.query.JcQueryResult;
import iot.jcypher.query.api.IClause;
import iot.jcypher.query.factories.clause.CREATE;
import iot.jcypher.query.result.JcError;
import iot.jcypher.query.values.JcNode;
import iot.jcypher.query.writer.Format;
import iot.jcypher.util.Util;

import java.util.*;

public class Neo4jRunner implements Runner {
    private IDBAccess dbAccess;
    private List<IClause> clauses;
    private List<JcNode> vertices;
    private String dburl;
    private String user;
    private String password;

    public Neo4jRunner(String dburl, String user, String password) {
        vertices = new ArrayList<>();
        clauses = new ArrayList<>();
        this.dburl = dburl;
        this.user = user;
        this.password = password;
    }

    public void start() {
        Properties props = new Properties();
        props.setProperty(DBProperties.SERVER_ROOT_URI, dburl);

        dbAccess = DBAccessFactory.createDBAccess(DBType.REMOTE, props, user, password);
    }

    public void addVertex(int mvertex) {
        vertices.add(new JcNode("Company_" + Integer.toString(mvertex)));
        clauses.add(CREATE.node(vertices.get(mvertex)).label("Company").property("name")
                .value("Company_" + Integer.toString(mvertex)));
    }

    public void addEdge(int medgeFrom, int medgeTo) {
        clauses.add(CREATE.node(vertices.get(medgeFrom)).relation().out().type("OWNS").node(vertices.get(medgeTo)));
    }

    public void stop() {
        String queryTitle = "Model Generator";
        JcQuery query = new JcQuery();
        query.setClauses(clauses.toArray(new IClause[clauses.size()]));
        print(query, queryTitle, Format.PRETTY_3);

        /** execute the query against a Neo4j database */
        JcQueryResult result = dbAccess.execute(query);
        if (result.hasErrors())
            printErrors(result);

        /** print the JSON representation of the query result */
        print(result, queryTitle);

        dbAccess.close();
    }

    private void print(JcQuery query, String title, Format format) {
        System.out.println("QUERY: " + title + " --------------------");
        // map to Cypher
        String cypher = iot.jcypher.util.Util.toCypher(query, format);
        System.out.println("CYPHER --------------------");
        System.out.println(cypher);

        // map to JSON
        String json = iot.jcypher.util.Util.toJSON(query, format);
        System.out.println("");
        System.out.println("JSON   --------------------");
        System.out.println(json);

        System.out.println("");
    }

    /**
     * print the JSON representation of the query result
     * @param queryResult
     */
    private void print(JcQueryResult queryResult, String title) {
        System.out.println("RESULT OF QUERY: " + title + " --------------------");
        String resultString = Util.writePretty(queryResult.getJsonResult());
        System.out.println(resultString);
    }

    private void print(List<GrNode> nodes, boolean distinct) {
        List<Long> ids = new ArrayList<Long>();
        StringBuilder sb = new StringBuilder();
        boolean firstNode = true;
        for (GrNode node : nodes) {
            if (!ids.contains(node.getId()) || !distinct) {
                ids.add(node.getId());
                if (!firstNode)
                    sb.append("\n");
                else
                    firstNode = false;
                sb.append("---NODE---:\n");
                sb.append('[');
                sb.append(node.getId());
                sb.append(']');
                for (GrLabel label : node.getLabels()) {
                    sb.append(", ");
                    sb.append(label.getName());
                }
                sb.append("\n");
                boolean first = true;
                for (GrProperty prop : node.getProperties()) {
                    if (!first)
                        sb.append(", ");
                    else
                        first = false;
                    sb.append(prop.getName());
                    sb.append(" = ");
                    sb.append(prop.getValue());
                }
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * print errors to System.out
     * @param result
     */
    private void printErrors(JcQueryResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------General Errors:");
        appendErrorList(result.getGeneralErrors(), sb);
        sb.append("\n---------------DB Errors:");
        appendErrorList(result.getDBErrors(), sb);
        sb.append("\n---------------stop Errors:");
        String str = sb.toString();
        System.out.println("");
        System.out.println(str);
    }

    /**
     * print errors to System.out
     * @param result
     */
    private void printErrors(List<JcError> errors) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------Errors:");
        appendErrorList(errors, sb);
        sb.append("\n---------------stop Errors:");
        String str = sb.toString();
        System.out.println("");
        System.out.println(str);
    }

    private void appendErrorList(List<JcError> errors, StringBuilder sb) {
        int num = errors.size();
        for (int i = 0; i < num; i++) {
            JcError err = errors.get(i);
            sb.append('\n');
            if (i > 0) {
                sb.append("-------------------\n");
            }
            sb.append("codeOrType: ");
            sb.append(err.getCodeOrType());
            sb.append("\nmessage: ");
            sb.append(err.getMessage());
            if (err.getAdditionalInfo() != null) {
                sb.append("\nadditional info: ");
                sb.append(err.getAdditionalInfo());
            }
        }
    }
}
