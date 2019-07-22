package org.donglight.campus.guide.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

/**
 * 两点之间的距离关系
 *
 * @author donglight
 * @date 2019/6/23
 * @since 1.0.0
 */
@RelationshipEntity(type = "DISTANCE")
@Getter
@Setter
public class Distance {

    @Id
    @GeneratedValue
    private Long distanceId;

    /**
     * 景点之间的距离多少米
     */
    @Property(name = "metre")
    private Long metre;

    /**
     * 步行大概需要多少分钟
     */
    @Property(name = "walkMinute")
    private int walkMinute;

    @StartNode
    @JsonIgnore
    private ScenicSpot start;

    @EndNode
    @JsonIgnore
    private ScenicSpot end;

    public Distance() {
    }

    public Distance(Long metre, ScenicSpot start, ScenicSpot end) {
        this.metre = metre;
        walkMinute = (int) (metre / 60);
        this.start = start;
        this.end = end;
    }
}
