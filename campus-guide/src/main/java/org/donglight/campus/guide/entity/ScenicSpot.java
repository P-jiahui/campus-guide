package org.donglight.campus.guide.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.Objects;

/**
 * 校园景点实体类
 *
 * @author donglight
 * @date 2019/6/23
 * @since 1.0.0
 */
@NodeEntity(label = "ScenicSpot")
@Getter
@Setter
public class ScenicSpot {

    @Id
    @GeneratedValue
    private Long scenicSpotId;

    /**
     * 景点代号
     */
    @Property(name = "code")
    private String code;

    /**
     * 景点名称
     */
    @Property(name = "name")
    private String name;

    /**
     * 景点简介信息
     */
    @Property(name = "introduction")
    private String introduction;

    /**
     * 图片地址
     */
    @Property(name = "imgUrl")
    private String imgUrl;

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

    /*@Relationship(type = "DISTANCE", direction = Relationship.UNDIRECTED)
    @JsonIgnoreProperties({"start", "end"})
    private List<Distance> distance;*/
    Integer schoolCode = -1;

    public ScenicSpot() {
    }

    public ScenicSpot(String code, String name, String introduction) {
        this.code = code;
        this.name = name;
        this.introduction = introduction;
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
        ScenicSpot that = (ScenicSpot) o;
        return createTimestamp == that.createTimestamp &&
                updateTimestamp == that.updateTimestamp &&
                Objects.equals(scenicSpotId, that.scenicSpotId) &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(introduction, that.introduction) &&
                Objects.equals(imgUrl, that.imgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scenicSpotId, code, name, introduction, imgUrl, createTimestamp, updateTimestamp);
    }
}
