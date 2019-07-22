package org.donglight.campus.guide.repository;

import org.donglight.campus.guide.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * SchoolRepository
 *
 * @author donglight
 * @date 2019/6/24
 * @since 1.0.0
 */
public interface SchoolRepository extends Neo4jRepository<School, Long> {

    /**
     * 根据关键字分页查询学校
     *
     * @param keywords 查找学校的关键字
     * @param pageable pageable
     * @return Page<ScenicSpot>
     */
    Page<School> findSchoolByNameRegex(String keywords, Pageable pageable);

    School findSchoolByName(String name);
}
