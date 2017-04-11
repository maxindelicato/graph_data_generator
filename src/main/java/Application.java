import com.pattern.generator.model.BarabasiAlbertGenerator;
import com.pattern.generator.model.DorogovtsevMendesGenerator;
import com.pattern.generator.model.ErdosRenyiGenerator;
import com.pattern.generator.model.WattsStrogatzGenerator;
import com.pattern.generator.runner.Neo4jRunner;
import org.apache.commons.cli.*;

public class Application {

    public static void main(String[] args) {
        // create the command line parser
        CommandLineParser parser = new DefaultParser();

        // create the Options
        Options options = new Options();
        options.addOption( "h", "help", false, "Show usage" );
        options.addOption( "b", "dburl", true, "Database URL" );
        options.addOption( "u", "user", true, "Database user name");
        options.addOption( "p", "password", true, "Database user password");
        options.addOption( "M", "model", true, "Specify generator model:" + System.lineSeparator() +
                "0 = Barabási–Albert Model (requires -n, -m, -s)" + System.lineSeparator() +
                "1 = Dorogovtsev-Mendes Model (requires -n -s)" + System.lineSeparator() +
                "2 = Erdős–Rényi Model (requires -n, -p, -s, -d)" + System.lineSeparator() +
                "3 = Watts-Strogatz Model (requires -n, -k, -p, -s");
        options.addOption( "n", "n-value", true, "Model specific n-value (int)");
        options.addOption( "m", "m-value", true, "Model specific m-value (int)");
        options.addOption( "p", "probability", true, "Model specific probability (double)");
        options.addOption( "k", "k-value", true, "Model specific k-value (int)");
        options.addOption( "d", "directed", true, "Enable directed edges (boolean)");
        options.addOption( "s", "seed", true, "Specify seed value for deterministic reproducibility (int)");

        try {
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );

            if (line.hasOption("help")) {
                // automatically generate the help statement
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp( "morpheus-graph-data-generator", options );
            }

//            Properties props = new Properties();
//            props.setProperty(DBProperties.SERVER_ROOT_URI,
//                    "http://autogenneo4j.sb02.stations.graphenedb.com:24789/");
//            props.setProperty(,
//                    "http://autogenneo4j.sb02.stations.graphenedb.com:24789/");
//
//            "autogen_neo4j", "j0c7JgUjKRjvemf3rn0y"

            if (line.hasOption("model")) {
                int model = Integer.parseInt(line.getOptionValue("model"));
                if (model == 0 && line.hasOption("n-value") && line.hasOption("m-value") && line.hasOption("seed")) {
                        BarabasiAlbertGenerator.generate(Integer.parseInt(line.getOptionValue("n-value")),
                                Integer.parseInt(line.getOptionValue("m-value")),
                                Integer.parseInt(line.getOptionValue("seed")),
                                new Neo4jRunner(line.getOptionValue("dburl"), line.getOptionValue("user"),
                                        line.getOptionValue("password")));
                } else if (model == 1 && line.hasOption("n-value") && line.hasOption("seed")) {
                    DorogovtsevMendesGenerator.generate(Integer.parseInt(line.getOptionValue("n-value")),
                            Integer.parseInt(line.getOptionValue("seed")),
                            new Neo4jRunner(line.getOptionValue("dburl"), line.getOptionValue("user"),
                                    line.getOptionValue("password")));
                } else if (model == 2 && line.hasOption("n-value") && line.hasOption("probability")
                        && line.hasOption("seed") && line.hasOption("directed")) {
                    ErdosRenyiGenerator.generate(Integer.parseInt(line.getOptionValue("n-value")),
                            Double.parseDouble(line.getOptionValue("probability")),
                            Integer.parseInt(line.getOptionValue("seed")),
                            Boolean.parseBoolean(line.getOptionValue("directed")),
                            new Neo4jRunner(line.getOptionValue("dburl"), line.getOptionValue("user"),
                                    line.getOptionValue("password")));
                } else if (model == 3 && line.hasOption("n-value") && line.hasOption("k-value")
                        && line.hasOption("probability") && line.hasOption("seed")) {
                    WattsStrogatzGenerator.generate(Integer.parseInt(line.getOptionValue("n-value")),
                            Integer.parseInt(line.getOptionValue("k-value")),
                            Double.parseDouble(line.getOptionValue("probability")),
                            Integer.parseInt(line.getOptionValue("seed")),
                            new Neo4jRunner(line.getOptionValue("dburl"), line.getOptionValue("user"),
                                    line.getOptionValue("password")));
                } else {
                    System.out.println("Invalid model specified.");
                }
            }
        }
        catch( ParseException exp ) {
            System.out.println( "Unexpected exception:" + exp.getMessage() );
        }

        // TODO: CLI switch command and config
    }
}

