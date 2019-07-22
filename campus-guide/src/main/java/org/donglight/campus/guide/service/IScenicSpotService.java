package org.donglight.campus.guide.service;

import org.donglight.campus.guide.entity.ScenicSpot;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 景点服务接口
 *
 * @author donglight
 * @date 2019/6/23
 * @since 1.0.0
 */
public interface IScenicSpotService {


    /**
     * 根据ID获取景点
     *
     * @param id id
     * @return ScenicSpot
     */
    ScenicSpot getScenicSpot(long id);

    /**
     * 根据keywords景点分页查询列表
     *
     * @param schoolCode schoolCode
     * @param name       name
     * @param page       page
     * @param pageSize   pageSize
     * @return Page<ScenicSpot>
     */
    Page<ScenicSpot> listScenicSpot(Integer schoolCode, String name, Integer page, Integer pageSize);

    /**
     * 根据keywords景点查询所有列表
     *
     * @param schoolCode schoolCode
     * @param name       name
     * @return Page<ScenicSpot>
     */
    List<ScenicSpot> listScenicSpot(Integer schoolCode, String name);


    /**
     * 查找两个定点之间的最短路线
     *
     * @param start 起点
     * @param end   终点
     * @return List<ScenicSpot>
     */
    List<ScenicSpot> findShortestRoute(String start, String end);

    /**
     * 查找两个节点之间的几条路线
     *
     * @param start 起点
     * @param end   终点
     * @return List<ScenicSpot>
     */
    List<ScenicSpot> findRoutes(String start, String end);

    /**
     * 更新景点信息
     *
     * @param scenicSpot scenicSpot
     * @param file       所对应的景点图片
     * @return ScenicSpot
     * @throws IOException 上传文件异常
     */
    ScenicSpot updateScenicSpot(ScenicSpot scenicSpot, MultipartFile file) throws IOException;

    /**
     * 删除一个景点
     *
     * @param scenicSpotId scenicSpotId
     */
    void deleteScenicSpot(Long scenicSpotId);

    /**
     * 添加一个景点
     *
     * @param scenicSpot 景点实体对象
     * @param file       所对应的景点图片
     * @return 新增加的实体
     * @throws IOException 上传文件异常
     */
    ScenicSpot addScenicSpot(ScenicSpot scenicSpot, MultipartFile file) throws IOException;

    /**
     * 最短路径距离
     *
     * @param start 起点
     * @param end   终点
     * @return 距离
     */
    Long findShortestRouteDistance(String start, String end);

    /**
     * 添加两个景点之间的路线
     *
     * @param start 起点
     * @param end   终点
     * @param distance   距离
     * @return boolean 是否添加成功
     */
    boolean addRoute(String start, String end, Long distance);
}
