package org.donglight.campus.guide.repository;

import org.donglight.campus.guide.entity.ScenicSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ScenicSpotRepository
 *
 * @author donglight
 * @date 2019/6/22
 * @since 1.0.0
 */
@Repository
public interface ScenicSpotRepository extends Neo4jRepository<ScenicSpot, Long> {

    /**
     * 查找两个节点之间的几条路线
     *
     * @param start start
     * @param end   end
     * @return List<ScenicSpot>
     */
    @Query("MATCH (start:ScenicSpot {name: {0}}), (end:ScenicSpot {name: {1}}) " +
            "MATCH route=(start)-[:DISTANCE*..10]-(end)" +
            "WITH route,reduce(s = 0, r IN rels(route) | s + r.metre) AS totalDistance " +
            "RETURN route ORDER BY totalDistance ASC LIMIT 4")
    List<ScenicSpot> findRoutes(String start, String end);

    /**
     * 查找两个节点之间的最短路线
     *
     * @param start start
     * @param end   end
     * @return List<ScenicSpot>
     */
    @Query("MATCH (start:ScenicSpot), (end:ScenicSpot) " +
            "where start.name=~{0} and end.name=~{1}" +
            "MATCH route=(start)-[:DISTANCE*..10]-(end)" +
            "WITH route,reduce(s = 0, r IN rels(route) | s + r.metre) AS totalDistance " +
            "RETURN route, totalDistance ORDER BY totalDistance ASC limit 1")
    List<ScenicSpot> findShortestRoute(String start, String end);

    /**
     * 获取最短路径距离
     *
     * @param start start
     * @param end   end
     * @return Distance
     */
    @Query("MATCH (start:ScenicSpot), (end:ScenicSpot) " +
            "where start.name=~{0} and end.name=~{1}" +
            "MATCH route=(start)-[:DISTANCE*..10]-(end)" +
            "WITH route,reduce(s = 0, r IN rels(route) | s + r.metre) AS metre " +
            "RETURN metre ORDER BY metre ASC limit 1")
    Long findShortestRouteDistance(String start, String end);

    /**
     * 根据学校名称和关键字分页查询景点
     *
     * @param schoolCode 学校代码
     * @param keywords   查找景点的关键字
     * @param pageable   pageable
     * @return Page<ScenicSpot>
     */
    @Query(value = "MATCH (sp:ScenicSpot)-[:CONTAIN]-(sc:School) where sc.code={0} and sp.name=~{1} return sp ORDER BY sp.code ASC",
            countQuery = "MATCH p=(sp:ScenicSpot)-[:CONTAIN]-(sc:School) where sc.code={0} and sp.name=~{1} return count(p)")
    Page<ScenicSpot> findScenicSpotBySchoolCodeAndScenicName(int schoolCode, String keywords, PageRequest pageable);

    /**
     * 根据学校名称和关键字查询所有景点
     *
     * @param schoolCode 学校代码
     * @param keywords   查找景点的关键字
     * @return Page<ScenicSpot>
     */
    @Query(value = "MATCH (sp:ScenicSpot)-[:CONTAIN]-(sc:School) where sc.code={0} and sp.name=~{1} return sp ORDER BY sp.code ASC")
    List<ScenicSpot> findScenicSpotBySchoolCodeAndScenicName(int schoolCode, String keywords);

    /**
     * 分页查询学校的景点信息
     *
     * @param schoolCode schoolCode
     * @param pageable   pageable
     * @return Page<ScenicSpot>
     */
    @Query(value = "MATCH (sp:ScenicSpot)-[:CONTAIN]-(sc:School) where sc.code={0} return sp ORDER BY sp.code ASC",
            countQuery = "MATCH p=(sp:ScenicSpot)-[:CONTAIN]-(sc:School) where sc.code={0} return count(p)")
    Page<ScenicSpot> findScenicSpotBySchoolCode(int schoolCode, PageRequest pageable);

    /**
     * 查询学校的所有景点
     *
     * @param schoolCode schoolCode
     * @return Page<ScenicSpot>
     */
    @Query(value = "MATCH (sp:ScenicSpot)-[:CONTAIN]-(sc:School) where sc.code={0} return sp ORDER BY sp.code ASC")
    List<ScenicSpot> findScenicSpotBySchoolCode(int schoolCode);

    /**
     * 建立景点和学校之间的关系
     *
     * @param scenicSpotId scenicSpotId
     * @param schoolCode   schoolCode
     */
    @Query(value = "MATCH (sp:ScenicSpot),(sc:School) WHERE id(sp)={0} AND sc.code={1} CREATE (sp)<-[r:CONTAIN]-(sc) RETURN r")
    void createRelationshipBetweenSchoolAndScenicSpot(Long scenicSpotId, int schoolCode);


    /**
     * 建立两个景点之间的直接相连关系
     *
     * @param start    start
     * @param end      end
     * @param distance distance
     * @return boolean
     */
    @Query(value = "MATCH (start:ScenicSpot),(end:ScenicSpot) WHERE start.name={0} AND end.name={1} CREATE (start)-[r:DISTANCE{metre:{2}}]->(end) RETURN true")
    boolean addRoute(String start, String end, long distance);
}
