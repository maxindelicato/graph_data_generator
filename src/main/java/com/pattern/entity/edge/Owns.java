package com.pattern.entity.edge;

import com.pattern.entity.vertex.Company;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class Owns {
    @Getter @Setter
    private Company ownedByCompany;

    @Getter @Setter
    private Company ownedCompany;

    @Getter @Setter
    private Date effectiveStartDate;

    @Getter @Setter
    private Date effectiveEndDate;
}
