package org.donglight.campus.guide.entity;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

/**
 * 编辑学校信息的人
 *
 * @author donglight
 * @date 2019/6/24
 * @since 1.0.0
 */
@NodeEntity(label = "SchoolEditor")
@Getter
@Setter
public class SchoolEditor {

    @Id
    @GeneratedValue
    private Long editorId;

    @Property(name = "loginName")
    private String loginName;

    @Property(name = "password")
    private String password;

    @Property(name = "realName")
    private String realName;

    @Relationship(type = "EDIT_SC")
    private School school;
}
