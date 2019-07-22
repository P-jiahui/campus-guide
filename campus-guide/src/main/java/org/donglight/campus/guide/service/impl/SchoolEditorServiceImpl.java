package org.donglight.campus.guide.service.impl;

import org.donglight.campus.guide.entity.SchoolEditor;
import org.donglight.campus.guide.repository.SchoolEditorRepository;
import org.donglight.campus.guide.service.ISchoolEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * SchoolEditorServiceImpl
 *
 * @author donglight
 * @date 2019/6/24
 * @since 1.0.0
 */
@Service
public class SchoolEditorServiceImpl implements ISchoolEditorService {

    private final SchoolEditorRepository schoolEditorRepository;

    @Autowired
    public SchoolEditorServiceImpl(SchoolEditorRepository schoolEditorRepository) {
        this.schoolEditorRepository = schoolEditorRepository;
    }

    @Override
    public SchoolEditor login(SchoolEditor schoolEditor) {
        String loginName = schoolEditor.getLoginName();
        String password = schoolEditor.getPassword();
        if (loginName == null || password == null) {
            return null;
        }
        loginName = loginName.trim();
        password = password.trim();
        if ("".equals(loginName) || "".equals(password)) {
            return null;
        }
        Optional<SchoolEditor> dbSchoolEditorOp = schoolEditorRepository.findSchoolEditorByLoginName(loginName);
        if (dbSchoolEditorOp.isPresent()) {
            SchoolEditor dbSchoolEditor = dbSchoolEditorOp.get();
            if (password.equals(dbSchoolEditor.getPassword())) {
                return dbSchoolEditor;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
