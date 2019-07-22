package org.donglight.campus.guide.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.donglight.campus.guide.entity.ScenicSpot;
import org.donglight.campus.guide.repository.ScenicSpotRepository;
import org.donglight.campus.guide.service.IScenicSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 景点服务实现类
 *
 * @author donglight
 * @date 2019/6/23
 * @since 1.0.0
 */
@Service
public class ScenicSpotServiceImpl implements IScenicSpotService {


    private final ScenicSpotRepository scenicSpotRepository;

    @Value("${image.url.prefix}")
    private String imgUrlPrefix;

    @Value("${static.resource.dir}")
    private String staticDir;

    @Autowired
    public ScenicSpotServiceImpl(ScenicSpotRepository scenicSpotRepository) {
        this.scenicSpotRepository = scenicSpotRepository;
    }

    @Override
    public ScenicSpot getScenicSpot(long id) {
        return scenicSpotRepository.findById(id).orElse(null);
    }

    @Override
    public Page<ScenicSpot> listScenicSpot(Integer schoolCode, String name, Integer page, Integer pageSize) {
        if (schoolCode == null) {
            return null;
        }

        if (page == null || page < 0) {
            page = 0;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 5;
        }
        PageRequest pageable = PageRequest.of(page, pageSize);
        if (name != null && name.trim().length() > 0) {
            return scenicSpotRepository.findScenicSpotBySchoolCodeAndScenicName(schoolCode, ".*" + name + ".*", pageable);
        }
        return scenicSpotRepository.findScenicSpotBySchoolCode(schoolCode, pageable);
    }

    @Override
    public List<ScenicSpot> listScenicSpot(Integer schoolCode, String name) {
        if (schoolCode == null) {
            return null;
        }
        if (StringUtils.isNotEmpty(name)) {
            return scenicSpotRepository.findScenicSpotBySchoolCodeAndScenicName(schoolCode, ".*" + name + ".*");
        }
        return scenicSpotRepository.findScenicSpotBySchoolCode(schoolCode);
    }

    @Override
    public List<ScenicSpot> findShortestRoute(String startScenicSpot, String endScenicSpot) {
        if (StringUtils.isEmpty(startScenicSpot) || StringUtils.isEmpty(endScenicSpot)) {
            return null;
        }
        return scenicSpotRepository.findShortestRoute(".*" + startScenicSpot + ".*", ".*" + endScenicSpot + ".*");
    }

    @Override
    public Long findShortestRouteDistance(String startScenicSpot, String endScenicSpot) {
        if (StringUtils.isEmpty(startScenicSpot) || StringUtils.isEmpty(endScenicSpot)) {
            return null;
        }
        return scenicSpotRepository.findShortestRouteDistance(".*" + startScenicSpot + ".*", ".*" + endScenicSpot + ".*");
    }

    @Override
    public boolean addRoute(String start, String end, Long distance) {
        if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end) || distance == null) {
            return false;
        }
        return scenicSpotRepository.addRoute(start.trim(), end.trim(), distance);
    }

    @Override
    public List<ScenicSpot> findRoutes(String start, String end) {
        if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
            return null;
        }
        if (start.length() > 50 && end.length() > 50) {
            return null;
        }
        if (start.equals(end)) {
            return null;
        }
        return scenicSpotRepository.findRoutes(start.trim(), end.trim());
    }

    @Override
    public ScenicSpot updateScenicSpot(ScenicSpot scenicSpot, MultipartFile file) throws IOException {
        if (scenicSpot != null) {
            saveUploadPicture(scenicSpot, file);
            scenicSpot.setUpdateTimestamp(System.currentTimeMillis());
            return scenicSpotRepository.save(scenicSpot);
        }
        return null;
    }

    @Override
    public void deleteScenicSpot(Long scenicSpotId) {
        //先删除该节点的所有关联关系
        scenicSpotRepository.deleteById(scenicSpotId);
    }

    @Override
    public ScenicSpot addScenicSpot(ScenicSpot scenicSpot, MultipartFile file) throws IOException {
        if (scenicSpot != null && scenicSpot.getSchoolCode() != null) {
            saveUploadPicture(scenicSpot, file);
            scenicSpot.setCreateTimestamp(System.currentTimeMillis());
            scenicSpot.setUpdateTimestamp(System.currentTimeMillis());
            ScenicSpot savedScenicSpot = scenicSpotRepository.save(scenicSpot);
            scenicSpotRepository.createRelationshipBetweenSchoolAndScenicSpot(savedScenicSpot.getScenicSpotId(), scenicSpot.getSchoolCode());
            return savedScenicSpot;
        }
        return null;
    }

    private void saveUploadPicture(ScenicSpot scenicSpot, MultipartFile pictureFile) throws IOException {
        if (pictureFile != null) {
            if (!org.springframework.util.StringUtils.isEmpty(pictureFile.getOriginalFilename())) {
                //获取classes目录绝对路径
                String path = ResourceUtils.getURL("classpath:").getPath();
                //如果上传目录为/static/images/，则可以如下获取：
                File realPath = new File(path, staticDir + imgUrlPrefix);
                //原始文件名称
                String pictureFileName = pictureFile.getOriginalFilename();
                //新文件名称
                String newFileName = "scenicSpot-" + scenicSpot.getCode() + pictureFileName.substring(pictureFileName.lastIndexOf("."));
                //上传图片
                File uploadPic = new File(realPath + File.separator + newFileName);
                //向磁盘写文件
                pictureFile.transferTo(uploadPic);
                scenicSpot.setImgUrl(imgUrlPrefix + "/" + newFileName);
            }
        }
    }
}
