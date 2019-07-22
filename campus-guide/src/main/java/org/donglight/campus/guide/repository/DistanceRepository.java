package org.donglight.campus.guide.repository;

import org.donglight.campus.guide.entity.Distance;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * DistanceRepository
 *
 * @author donglight
 * @date 2019/6/22
 * @since 1.0.0
 */
@Repository
public interface DistanceRepository extends Neo4jRepository<Distance, Long> {

}
