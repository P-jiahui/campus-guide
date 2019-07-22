package org.donglight.campus.guide.service;

import org.donglight.campus.guide.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * ISchoolService
 *
 * @author donglight
 * @date 2019/6/24
 * @since 1.0.0
 */
public interface ISchoolService {

    /**
     * 按条件分页查询学校列表
     *
     * @param name     name
     * @param page     第几页
     * @param pageSize 页大小
     * @return Page<School>
     */
    Page<School> listSchool(String name, int page, int pageSize);

    /**
     * 根据id查询
     *
     * @param id id
     * @return School
     */
    School getSchoolById(Long id);

    /**
     * 按照名称精确查询
     *
     * @param name name
     * @return School
     */
    School getSchoolByName(String name);

    /**
     * 更新学校信息
     *
     * @param school  学校实体
     * @param file    上传的文件实体
     * @throws IOException 文件上传异常
     * @return 更新后的学校实体
     */
    School updateSchool(School school, MultipartFile file) throws IOException;
}
