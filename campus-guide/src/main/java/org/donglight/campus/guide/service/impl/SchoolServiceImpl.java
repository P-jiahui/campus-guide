package org.donglight.campus.guide.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.donglight.campus.guide.entity.School;
import org.donglight.campus.guide.repository.SchoolRepository;
import org.donglight.campus.guide.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * ISchoolServiced实现类
 *
 * @author donglight
 * @date 2019/6/24
 * @since 1.0.0
 */
@Service
public class SchoolServiceImpl implements ISchoolService {

    private final SchoolRepository schoolRepository;

    @Value("${image.url.prefix}")
    private String imgUrlPrefix;

    @Value("${static.resource.dir}")
    private String staticDir;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public Page<School> listSchool(String name, int page, int pageSize) {
        if (page < 0) {
            page = 0;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        PageRequest pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "name");
        if (StringUtils.isNotEmpty(name)) {
            return schoolRepository.findSchoolByNameRegex(".*" + name + ".*", pageable);
        }

        return schoolRepository.findAll(pageable);
    }

    @Override
    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    @Override
    public School getSchoolByName(String name) {
        return schoolRepository.findSchoolByName(name);
    }

    @Override
    public School updateSchool(School school, MultipartFile file) throws IOException {
        if (StringUtils.isEmpty(school.getScenery()) || StringUtils.isEmpty(school.getName()) || StringUtils.isEmpty(school.getIntroduction())) {
            return null;
        }
        //保存上传的图片
        saveUploadPicture(school, file);
        school.setUpdateTimestamp(System.currentTimeMillis());
        //更新
        return schoolRepository.save(school, 0);
    }

    private void saveUploadPicture(School school, MultipartFile pictureFile) throws IOException {
        if (pictureFile != null) {
            if (!org.springframework.util.StringUtils.isEmpty(pictureFile.getOriginalFilename())) {
                //获取classes目录绝对路径
                String path = ResourceUtils.getURL("classpath:").getPath();
                //如果上传目录为/static/images/，则可以如下获取：
                File realPath = new File(path, staticDir + imgUrlPrefix);
                //原始文件名称
                String pictureFileName = pictureFile.getOriginalFilename();
                //新文件名称
                String newFileName = "planUrl-" + school.getCode() + pictureFileName.substring(pictureFileName.lastIndexOf("."));
                //上传图片
                File uploadPic = new File(realPath + File.separator + newFileName);
                //向磁盘写文件
                pictureFile.transferTo(uploadPic);
                school.setPlanUrl(imgUrlPrefix + "/" + newFileName);
            }
        }
    }
}
