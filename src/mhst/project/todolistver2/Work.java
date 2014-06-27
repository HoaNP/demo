package mhst.project.todolistver2;

public class Work {
	private String workContent;
	private String timeContent;
	private boolean isChecked;
	
	public Work(String workContent, String timeContent) {
		this.workContent = workContent;
		this.timeContent = timeContent;
		isChecked = false;
	}

	/**
	 * @return the isChecked
	 */
	public boolean isChecked() {
		return isChecked;
	}

	/**
	 * @param isChecked the isChecked to set
	 */
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	/**
	 * @return the workContent
	 */
	public String getContent() {
		return workContent;
	}

	/**
	 * @return the timeContent
	 */
	public String getTime() {
		return timeContent;
	}
	
	
}
