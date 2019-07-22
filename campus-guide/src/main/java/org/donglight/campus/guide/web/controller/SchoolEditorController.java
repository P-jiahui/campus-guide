package org.donglight.campus.guide.web.controller;

import org.donglight.campus.guide.entity.SchoolEditor;
import org.donglight.campus.guide.service.ISchoolEditorService;
import org.donglight.campus.guide.util.Response;
import org.donglight.campus.guide.util.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校编辑控制类
 *
 * @author donglight
 * @date 2019/6/24
 * @since 1.0.0
 */
@RestController
public class SchoolEditorController {

    private final ISchoolEditorService schoolEditorService;

    @Autowired
    public SchoolEditorController(ISchoolEditorService schoolEditorService) {
        this.schoolEditorService = schoolEditorService;
    }

    @PostMapping("login")
    public Response login(@RequestBody SchoolEditor schoolEditor) {
        SchoolEditor loginSchoolEditor = schoolEditorService.login(schoolEditor);
        if (loginSchoolEditor != null) {
            loginSchoolEditor.setPassword(null);
            return Response.ok(loginSchoolEditor);
        } else {
            return Response.exception(ResponseEnum.UNKNOWNACCOUNTEXCEPTION);
        }
    }

}
