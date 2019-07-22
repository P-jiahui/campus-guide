package org.donglight.campus.guide.service;

import org.donglight.campus.guide.entity.SchoolEditor;

/**
 * ISchoolEditorService
 *
 * @author donglight
 * @date 2019/6/24
 * @since 1.0.0
 */
public interface ISchoolEditorService {

    /**
     * 登录
     * @param schoolEditor schoolEditor
     * @return SchoolEditor
     */
    SchoolEditor login(SchoolEditor schoolEditor);
}
