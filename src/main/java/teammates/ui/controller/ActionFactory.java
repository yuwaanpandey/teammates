package teammates.ui.controller;

// CHECKSTYLE.OFF:AvoidStarImport as there would be many (>100) import lines added if we were to import all of the ActionURIs
import static teammates.common.util.Const.ActionURIs.*;
// CHECKSTYLE.ON:AvoidStarImport

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import teammates.common.exception.PageNotFoundException;
import teammates.common.exception.TeammatesException;
import teammates.common.util.Assumption;
import teammates.common.util.Logger;

/**
 * Is used to generate the matching {@link Action} for a given URI.
 */
public class ActionFactory {
    private static final Logger log = Logger.getLogger();

    private static Map<String, Class<? extends Action>> actionMappings = new HashMap<>();

    static {
        map(INSTRUCTOR_COURSES_PAGE, InstructorCoursesPageAction.class);
        map(INSTRUCTOR_COURSE_STATS_PAGE, CourseStatsPageAction.class);
        map(INSTRUCTOR_COURSE_ADD, InstructorCourseAddAction.class);
        map(INSTRUCTOR_COURSE_DELETE, InstructorCourseDeleteAction.class);
        map(INSTRUCTOR_COURSE_ARCHIVE, InstructorCourseArchiveAction.class);
        map(INSTRUCTOR_COURSE_SOFT_DELETED_COURSE_RESTORE, InstructorCourseRestoreSoftDeletedCourseAction.class);
        map(INSTRUCTOR_COURSE_SOFT_DELETED_COURSE_RESTORE_ALL, InstructorCourseRestoreAllSoftDeletedCoursesAction.class);
        map(INSTRUCTOR_COURSE_SOFT_DELETED_COURSE_DELETE, InstructorCourseDeleteSoftDeletedCourseAction.class);
        map(INSTRUCTOR_COURSE_SOFT_DELETED_COURSE_DELETE_ALL, InstructorCourseDeleteAllSoftDeletedCoursesAction.class);
        map(INSTRUCTOR_COURSE_EDIT_PAGE, InstructorCourseEditPageAction.class);
        map(INSTRUCTOR_COURSE_EDIT_SAVE, InstructorCourseEditSaveAction.class);
        map(INSTRUCTOR_COURSE_INSTRUCTOR_ADD, InstructorCourseInstructorAddAction.class);
        map(INSTRUCTOR_COURSE_INSTRUCTOR_EDIT_SAVE, InstructorCourseInstructorEditSaveAction.class);
        map(INSTRUCTOR_COURSE_INSTRUCTOR_DELETE, InstructorCourseInstructorDeleteAction.class);
        map(INSTRUCTOR_COURSE_ENROLL_PAGE, InstructorCourseEnrollPageAction.class);
        map(INSTRUCTOR_COURSE_ENROLL_AJAX_PAGE, InstructorCourseEnrollAjaxPageAction.class);
        map(INSTRUCTOR_COURSE_ENROLL_SAVE, InstructorCourseEnrollSaveAction.class);
        map(INSTRUCTOR_EDIT_STUDENT_FEEDBACK_PAGE, InstructorEditStudentFeedbackPageAction.class);
        map(INSTRUCTOR_EDIT_STUDENT_FEEDBACK_SAVE, InstructorEditStudentFeedbackSaveAction.class);
        map(INSTRUCTOR_EDIT_INSTRUCTOR_FEEDBACK_PAGE, InstructorEditInstructorFeedbackPageAction.class);
        map(INSTRUCTOR_EDIT_INSTRUCTOR_FEEDBACK_SAVE, InstructorEditInstructorFeedbackSaveAction.class);
        map(INSTRUCTOR_FEEDBACK_SESSIONS_PAGE, InstructorFeedbackSessionsPageAction.class);
        map(INSTRUCTOR_FEEDBACK_ADD, InstructorFeedbackAddAction.class);
        map(INSTRUCTOR_FEEDBACK_EDIT_COPY_PAGE, InstructorFeedbackEditCopyPageAction.class);
        map(INSTRUCTOR_FEEDBACK_EDIT_COPY, InstructorFeedbackEditCopyAction.class);
        map(INSTRUCTOR_FEEDBACK_REMIND, InstructorFeedbackRemindAction.class);
        map(INSTRUCTOR_FEEDBACK_REMIND_PARTICULAR_STUDENTS_PAGE, InstructorFeedbackRemindParticularStudentsPageAction.class);
        map(INSTRUCTOR_FEEDBACK_REMIND_PARTICULAR_STUDENTS, InstructorFeedbackRemindParticularStudentsAction.class);
        map(INSTRUCTOR_FEEDBACK_PUBLISH, InstructorFeedbackPublishAction.class);
        map(INSTRUCTOR_FEEDBACK_RESEND_PUBLISHED_EMAIL_PAGE,
                InstructorFeedbackResendPublishedEmailPageAction.class);
        map(INSTRUCTOR_FEEDBACK_RESEND_PUBLISHED_EMAIL,
                InstructorFeedbackResendPublishedEmailAction.class);
        map(INSTRUCTOR_FEEDBACK_UNPUBLISH, InstructorFeedbackUnpublishAction.class);
        map(INSTRUCTOR_FEEDBACK_SOFT_DELETED_SESSION_RESTORE, InstructorFeedbackRestoreSoftDeletedSessionAction.class);
        map(INSTRUCTOR_FEEDBACK_SOFT_DELETED_SESSION_RESTORE_ALL,
                InstructorFeedbackRestoreAllSoftDeletedSessionsAction.class);
        map(INSTRUCTOR_FEEDBACK_SOFT_DELETED_SESSION_DELETE, InstructorFeedbackDeleteSoftDeletedSessionAction.class);
        map(INSTRUCTOR_FEEDBACK_SOFT_DELETED_SESSION_DELETE_ALL, InstructorFeedbackDeleteAllSoftDeletedSessionsAction.class);
        map(INSTRUCTOR_FEEDBACK_QUESTION_ADD, InstructorFeedbackQuestionAddAction.class);
        map(INSTRUCTOR_FEEDBACK_QUESTION_COPY_PAGE, InstructorFeedbackQuestionCopyPageAction.class);
        map(INSTRUCTOR_FEEDBACK_QUESTION_COPY, InstructorFeedbackQuestionCopyAction.class);
        map(INSTRUCTOR_FEEDBACK_QUESTION_EDIT, InstructorFeedbackQuestionEditAction.class);
        map(INSTRUCTOR_FEEDBACK_QUESTION_VISIBILITY_MESSAGE, InstructorFeedbackQuestionVisibilityMessageAction.class);
        map(INSTRUCTOR_FEEDBACK_RESULTS_PAGE, InstructorFeedbackResultsPageAction.class);
        map(INSTRUCTOR_FEEDBACK_RESULTS_DOWNLOAD, InstructorFeedbackResultsDownloadAction.class);
        map(INSTRUCTOR_FEEDBACK_RESPONSE_COMMENT_ADD, InstructorFeedbackResponseCommentAddAction.class);
        map(INSTRUCTOR_FEEDBACK_RESPONSE_COMMENT_EDIT, InstructorFeedbackResponseCommentEditAction.class);
        map(INSTRUCTOR_FEEDBACK_RESPONSE_COMMENT_DELETE, InstructorFeedbackResponseCommentDeleteAction.class);
        map(INSTRUCTOR_FEEDBACK_PREVIEW_ASSTUDENT, InstructorFeedbackPreviewAsStudentAction.class);
        map(INSTRUCTOR_FEEDBACK_PREVIEW_ASINSTRUCTOR, InstructorFeedbackPreviewAsInstructorAction.class);
        map(INSTRUCTOR_FEEDBACK_SUBMISSION_EDIT_PAGE, InstructorFeedbackSubmissionEditPageAction.class);
        map(INSTRUCTOR_FEEDBACK_SUBMISSION_EDIT_SAVE, InstructorFeedbackSubmissionEditSaveAction.class);
        map(INSTRUCTOR_HOME_PAGE, InstructorHomePageAction.class);
        map(INSTRUCTOR_SEARCH_PAGE, InstructorSearchPageAction.class);
        map(INSTRUCTOR_STUDENT_LIST_PAGE, InstructorStudentListPageAction.class);
        map(INSTRUCTOR_STUDENT_LIST_AJAX_PAGE, InstructorStudentListAjaxPageAction.class);
        map(INSTRUCTOR_STUDENT_RECORDS_AJAX_PAGE, InstructorStudentRecordsAjaxPageAction.class);

        map(STUDENT_FEEDBACK_RESULTS_PAGE, StudentFeedbackResultsPageAction.class);
        map(STUDENT_FEEDBACK_SUBMISSION_EDIT_PAGE, StudentFeedbackSubmissionEditPageAction.class);
        map(STUDENT_FEEDBACK_SUBMISSION_EDIT_SAVE, StudentFeedbackSubmissionEditSaveAction.class);
        map(FEEDBACK_PARTICIPANT_FEEDBACK_RESPONSE_COMMENT_DELETE,
                FeedbackParticipantFeedbackResponseCommentDeleteAction.class);
        map(STUDENT_PROFILE_PAGE, StudentProfilePageAction.class);
        map(STUDENT_PROFILE_PICTURE_UPLOAD, StudentProfilePictureUploadAction.class);
        map(STUDENT_PROFILE_PICTURE_EDIT, StudentProfilePictureEditAction.class);
        map(STUDENT_PROFILE_CREATEUPLOADFORMURL, StudentProfileCreateFormUrlAction.class);
        map(STUDENT_PROFILE_EDIT_SAVE, StudentProfileEditSaveAction.class);

        map(CREATE_IMAGE_UPLOAD_URL, CreateImageUploadUrlAction.class);
        map(IMAGE_UPLOAD, ImageUploadAction.class);
    }

    /**
     * Returns the matching {@link Action} object for the URI in the {@code req}.
     */
    public Action getAction(HttpServletRequest req) {

        String url = req.getRequestURL().toString();
        log.info("URL received : [" + req.getMethod() + "] " + url);

        String uri = req.getRequestURI();
        if (uri.contains(";")) {
            uri = uri.split(";")[0];
        }
        Action c = getAction(uri);
        c.init(req);
        return c;

    }

    private static Action getAction(String uri) {
        Class<? extends Action> controllerClass = actionMappings.get(uri);

        if (controllerClass == null) {
            throw new PageNotFoundException(uri);
        }

        try {
            return controllerClass.newInstance();
        } catch (Exception e) {
            Assumption.fail("Could not create the action for " + uri + ": "
                            + TeammatesException.toStringWithStackTrace(e));
            return null;

        }

    }

    private static void map(String actionUri, Class<? extends Action> actionClass) {
        actionMappings.put(actionUri, actionClass);
    }

}
