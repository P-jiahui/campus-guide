package org.donglight.campus.guide.bak;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * 电影
 *
 * @author donglight
 * @date 2019/6/22
 * @since 1.0.0
 */
@NodeEntity(label = "movie")
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue
    Long id;

    @Property(name = "released")
    private Long released;

    @Property(name = "tagline")
    private String tagline;

    @Property(name = "title")
    private String title;
}
