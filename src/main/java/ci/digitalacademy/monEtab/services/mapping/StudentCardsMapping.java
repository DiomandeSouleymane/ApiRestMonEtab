package ci.digitalacademy.monEtab.services.mapping;

import ci.digitalacademy.monEtab.models.StudentCards;
import ci.digitalacademy.monEtab.models.User;
import ci.digitalacademy.monEtab.services.dto.StudentCardsDTO;
import ci.digitalacademy.monEtab.services.dto.UserDTO;

public final class StudentCardsMapping {

    private StudentCardsMapping() {
    }

    public static void partialUpdate(StudentCards studentCards, StudentCardsDTO studentCardsDTO) {
        if (studentCardsDTO.getExpirationDate() != null) {
            studentCards.setExpirationDate(studentCardsDTO.getExpirationDate());
        }
        if (studentCardsDTO.getReference() != null) {
            studentCards.setReference(studentCardsDTO.getReference());
        }
        if (studentCardsDTO.getIssueDate() != null) {
            studentCards.setIssueDate(studentCardsDTO.getIssueDate());
        }
    }
}
