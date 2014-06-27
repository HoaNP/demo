package mhst.project.todolistver2;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomViewGroup extends LinearLayout {

	public CheckBox cb;
	public TextView workContent;
	public TextView timeContent;
	
	public CustomViewGroup(Context context) {
		super(context);
		LayoutInflater li = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.list, this, true);
		cb = (CheckBox)findViewById(R.id.check_work);
		workContent = (TextView)findViewById(R.id.work_content);
		timeContent = (TextView)findViewById(R.id.time_content);
	}
	
}
