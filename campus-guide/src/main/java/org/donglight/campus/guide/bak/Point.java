package org.donglight.campus.guide.bak;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * 功能
 *
 * @author donglight
 * @date 2019/6/23
 * @since 1.0.0
 */
@NodeEntity(label = "Point")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class Point {

    private Long id;

    @Property(name = "title")
    private String title;


}
