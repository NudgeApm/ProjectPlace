package com.nla.domain;

public class Action extends ObjectBase implements java.io.Serializable {

    private final static String SELECTION_NEW = "New Selection";
    private final static String SELECTION_DONE = "Violation Removed";
    private final static String SELECTION_STILL = "Still a Violation";

    private final static String PRIORITY_EXTREME = "Extreme";
    private final static String PRIORITY_HIGH = "High";
    private final static String PRIORITY_MOD = "Moderate";
    private static final String PRIORITY_LOW = "Low";


    /**
     * The comment.
     */
    private String comment;


    private String objectName;

    /**
     * The user manager.
     */
    private String manager;

    private String setDate;

    /**
     * The priority.
     */
    private String priority;

    /**
     * The violation.
     */
    private int objectId;

    /**
     * The violation.
     */
    private String objectFullName;

    /**
     * The violation.
     */
    private int ruleId;

    /**
     * The violation.
     */
    private String ruleName;

    /**
     * The status.
     */
    private String status;

    /**
     * The selection snapshot id.
     */
    private Integer selectionSnapshotId;

    /**
     * The deselection snapshot id.
     */
    private Integer deselectionSnapshotId;

    // Default empty constructor for JABX
    public Action() {
        // NOP
    }


    public Action(final String objectFullName, final int objectId, final String ruleName, final int ruleId, final String comment, final String manager, final String priority, final String status) {

        this.comment = comment;
        this.manager = manager;
        setPriority(priority);
        this.ruleName = ruleName;
        this.ruleId = ruleId;
        this.objectFullName = objectFullName;
        this.objectId = objectId;
        setStatus(status);
    }

    /**
     * Gets the violation.
     *
     * @return the violation
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * Sets the violation.
     *
     * @param ruleName the new violation
     */
    public void setRuleName(final String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * Gets the violation.
     *
     * @return the violation
     */
    public String getObjectFullName() {
        return objectFullName;
    }

    /**
     * Sets the violation.
     *
     * @param objectFullName the new violation
     */
    public void setObjectFullName(final String objectFullName) {
        this.objectFullName = objectFullName;
    }

    /**
     * Gets the violation.
     *
     * @return the violation
     */
    public int getRuleId() {
        return ruleId;
    }

    /**
     * Sets the violation.
     *
     * @param ruleId the new violation
     */
    public void setRuleId(final int ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * Gets the violation.
     *
     * @return the violation
     */
    public int getObjectId() {
        return objectId;
    }

    /**
     * Sets the violation.
     *
     * @param objectId the new violation
     */
    public void setObjectId(final int objectId) {
        this.objectId = objectId;
    }

    /**
     * Gets the comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment.
     *
     * @param comment the new comment
     */
    public void setComment(final String comment) {
        this.comment = comment;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public String getManager() {
        return manager;
    }

    /**
     * Sets the user.
     *
     * @param manager the new user
     */
    public void setManager(final String manager) {
        this.manager = manager;
    }

    /**
     * Gets the priority.
     *
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the priority.
     *
     * @param priority the new priority
     */
    public void setPriority(final String priority) {
        if ("1".equals(priority))
            this.priority = PRIORITY_EXTREME;
        else if ("2".equals(priority))
            this.priority = PRIORITY_HIGH;
        else if ("3".equals(priority))
            this.priority = PRIORITY_MOD;
        else if ("4".equals(priority))
            this.priority = PRIORITY_LOW;
        else
            this.priority = priority;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(final String status) {
        if ("1".equals(status))
            this.status = SELECTION_NEW;
        else if ("2".equals(status))
            this.status = SELECTION_DONE;
        else if ("0".equals(status))
            this.status = SELECTION_STILL;
        else
            this.status = status;
    }


    /**
     * Gets the selection snapshot id.
     *
     * @return the selection snapshot id
     */
    public Integer getSelectionSnapshotId() {
        return selectionSnapshotId;
    }

    /**
     * Sets the selection snapshot id.
     *
     * @param selectionSnapshotId the new selection snapshot id
     */
    public void setSelectionSnapshotId(final Integer selectionSnapshotId) {
        this.selectionSnapshotId = selectionSnapshotId;
    }

    /**
     * Gets the deselection snapshot id.
     *
     * @return the deselection snapshot id
     */
    public Integer getDeselectionSnapshotId() {
        return deselectionSnapshotId;
    }

    /**
     * Sets the deselection snapshot id.
     *
     * @param selectionSnapshotId the new deselection snapshot id
     */
    public void setDeselectionSnapshotId(final Integer deselectionSnapshotId) {
        this.deselectionSnapshotId = deselectionSnapshotId;
    }


    public String getSetDate() {
        return setDate;
    }


    public void setSetDate(String setDate) {
        this.setDate = setDate;
    }


    public String getObjectName() {
        return objectName;
    }


    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }


}
