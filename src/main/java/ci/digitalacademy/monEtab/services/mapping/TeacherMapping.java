package ci.digitalacademy.monEtab.services.mapping;

import ci.digitalacademy.monEtab.models.Teacher;
import ci.digitalacademy.monEtab.services.dto.TeacherDTO;

public final class TeacherMapping {

    private TeacherMapping() {

    }

    public static void partialUpdate(Teacher teacher, TeacherDTO teacherDTO) {

        if (teacherDTO.getSpecialty() != null) {
            teacher.setSpecialty(teacherDTO.getSpecialty());
        }
        if (teacherDTO.getBirthday() != null) {
            teacher.setBirthday(teacherDTO.getBirthday());
        }
        if (teacherDTO.getPhoneNumber() != null) {
            teacher.setPhoneNumber(teacherDTO.getPhoneNumber());
        }

        if (teacherDTO.getGender() != null) {
            teacher.setGender(teacherDTO.getGender());
        }
        if (teacherDTO.isAvailable()) {
            teacher.setAvailable(true);
        }
        if (teacherDTO.getFirstName() != null) {
            teacher.setFirstName(teacherDTO.getFirstName());
        }
        if (teacherDTO.getLastName() != null) {
            teacher.setLastName(teacherDTO.getLastName());
        }


    }
}
