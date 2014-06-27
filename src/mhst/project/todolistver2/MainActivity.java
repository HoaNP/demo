package mhst.project.todolistver2;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends Activity {

	private static final int DELETE_WORK = Menu.FIRST;
	private static final int ABOUT = Menu.FIRST + 2;

	ArrayList<Work> array;
	ListWorkAdapter arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		array = new ArrayList<Work>();
		arrayAdapter = new ListWorkAdapter(this, R.layout.list, array);
		final EditText workEnter = (EditText) findViewById(R.id.work_enter);
		final EditText hourEdit = (EditText) findViewById(R.id.hour_edit);
		final EditText minuteEdit = (EditText) findViewById(R.id.minute_edit);
		final Button button = (Button) findViewById(R.id.button);

		final ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(arrayAdapter);
		OnClickListener add = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (workEnter.getText().toString().equals("")
						|| hourEdit.getText().toString().equals("")
						|| minuteEdit.getText().toString().equals("")) {
					showBuilder("Info missing",
							"Please enter all information of the work",
							"Continue");
				} else {
					String workContent = workEnter.getText().toString();
					String timeContent = hourEdit.getText().toString() + ":"
							+ minuteEdit.getText().toString();
					int hour = Integer.parseInt(hourEdit.getText().toString());
					int minute = Integer.parseInt(minuteEdit.getText()
							.toString());
					if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
						showBuilder(
								"Incorrect time",
								"Please enter hour between 0 and 23, \nminute betweet 0 and 59",
								"Continue");
					} else {
						Work work = new Work(workContent, timeContent);
						array.add(0, work);
						arrayAdapter.notifyDataSetChanged();
						workEnter.setText("");
						hourEdit.setText("");
						minuteEdit.setText("");
					}
				}
			}
		};
		button.setOnClickListener(add);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				showBuilder("Work info",
						"At " + array.get(position).getTime() + "\n"
								+ "you have "
								+ array.get(position).getContent(), "OK");
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, DELETE_WORK, 0, "Delete").setIcon(android.R.id.icon1);
		menu.add(0, ABOUT, 0, "About").setIcon(android.R.id.icon2);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_WORK: {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Confirm");
			builder.setMessage("Do you want to delete checked work(s) ?");
			builder.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							deleteCheckedWork();
						}
					});
			builder.setNegativeButton("No",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
			builder.show();
			break;
		}
		case ABOUT: {
			showBuilder("Mua he sang tao", "AUTHOR:" + "\n"
					+ "Nguyen Huu Thanh Canh" + "\n" + "SOURCE:" + "\n"
					+ "programmingforall.wordpress.com", "Close");
			break;
		}
		}
		return true;
	}

	private void deleteCheckedWork() {
		if (array.size() > 0) {
			for (int i = 0; i < array.size(); ++i) {
				if (array.get(i).isChecked()) {
					array.remove(i);
					arrayAdapter.notifyDataSetChanged();
					--i;
				}
			}
		}
	}

	private void showBuilder(String title, String message, String contentButton) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(contentButton,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				});
		builder.show();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
