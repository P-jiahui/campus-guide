package org.donglight.campus.guide.web.controller;

import org.donglight.campus.guide.entity.School;
import org.donglight.campus.guide.service.ISchoolService;
import org.donglight.campus.guide.util.Response;
import org.donglight.campus.guide.util.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 学校控制器
 *
 * @author donglight
 * @date 2019/6/24
 * @since 1.0.0
 */
@RestController
@RequestMapping("schools")
public class SchoolController {

    private final ISchoolService schoolService;


    @Autowired
    public SchoolController(ISchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("")
    public Response schools(@RequestParam(defaultValue = "") String name,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "0") int pageSize) {
        Page<School> schools = schoolService.listSchool(name.trim(), page, pageSize);
        if (schools != null) {
            return Response.ok(schools.getContent());
        } else {
            return Response.exception(ResponseEnum.NOELEMENT);
        }
    }

    @GetMapping("/accurate")
    public Response getSchoolByName(@RequestParam(defaultValue = "") String name) {
        School school = schoolService.getSchoolByName(name.trim());
        if (school != null) {
            return Response.ok(school);
        } else {
            return Response.exception(ResponseEnum.NOELEMENT);
        }
    }

    @GetMapping("{id}")
    public Response getSchoolById(@PathVariable Long id) {
        School school = schoolService.getSchoolById(id);
        if (school != null) {
            return Response.ok(school);
        } else {
            return Response.exception(ResponseEnum.NOELEMENT);
        }
    }

    @PutMapping(value = "")
    public Response editSchoolInfo(School school, MultipartFile file) throws IOException {
        School schoolUpdated = schoolService.updateSchool(school, file);
        if (schoolUpdated != null) {
            return Response.ok(schoolUpdated);
        } else {
            return Response.exception(ResponseEnum.NOELEMENT);
        }
    }


}
