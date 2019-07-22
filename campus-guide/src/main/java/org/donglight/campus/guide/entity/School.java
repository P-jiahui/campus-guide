package org.donglight.campus.guide.entity;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.Objects;

/**
 * 学校实体
 *
 * @author donglight
 * @date 2019/6/23
 * @since 1.0.0
 */
@NodeEntity(label = "School")
@Getter
@Setter
public class School {
    @Id
    @GeneratedValue
    private Long schoolId;

    /**
     * 学校代号
     */
    @Property(name = "code")

    private int code;

    /**
     * 学校全称
     */
    @Property(name = "name")
    private String name;

    /**
     * 学校平面图地址
     */
    @Property(name = "planUrl")
    private String planUrl;

    /**
     * 学校信息简介
     */
    @Property(name = "introduction")
    private String introduction;

    /**
     * 学校风光简介
     */
    @Property(name = "scenery")
    private String scenery;

    /**
     * 销毁图片url
     */
    @Property(name = "badgeUrl")
    private String badgeUrl;


    /**
     * 创建时间戳
     */
    @Property(name = "createTimestamp")
    private long createTimestamp;

    /**
     * 更新时间戳
     */
    @Property(name = "updateTimestamp")
    private long updateTimestamp;

    /*@Relationship(type = "CONTAIN", direction = Relationship.UNDIRECTED)
    @JsonIgnoreProperties({"start", "end"})
    private List<ScenicSpot> scenicSpots*/;

    public School() {
        updateTimestamp = System.currentTimeMillis();
    }

    public School(int code, String name, String introduction, String scenery) {
        this.code = code;
        this.name = name;
        this.introduction = introduction;
        this.scenery = scenery;
        createTimestamp = System.currentTimeMillis();
        updateTimestamp = System.currentTimeMillis();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        School school = (School) o;
        return code == school.code &&
                createTimestamp == school.createTimestamp &&
                updateTimestamp == school.updateTimestamp &&
                schoolId.equals(school.schoolId) &&
                name.equals(school.name) &&
                planUrl.equals(school.planUrl) &&
                introduction.equals(school.introduction) &&
                scenery.equals(school.scenery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolId, code, name, planUrl, introduction, scenery, createTimestamp, updateTimestamp);
    }
}
