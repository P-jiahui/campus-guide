package org.donglight.campus.guide.repository;

import org.donglight.campus.guide.entity.SchoolEditor;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

/**
 * SchoolEditorRepository
 *
 * @author donglight
 * @date 2019/6/24
 * @since 1.0.0
 */
public interface SchoolEditorRepository extends Neo4jRepository<SchoolEditor, Long> {

    /**
     * 根据登录名查找编辑者
     *
     * @param loginName 登录名
     * @return Optional<SchoolEditor>
     */
    Optional<SchoolEditor> findSchoolEditorByLoginName(String loginName);
}
