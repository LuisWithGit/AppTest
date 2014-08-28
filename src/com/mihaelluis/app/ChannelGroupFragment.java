package com.mihaelluis.app;

import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.linknet.service.tv.ServiceManagerContext;
import com.linknet.service.tv.channel.IChannelManager;
import com.mihaelluis.app.customview.LogTextBox;
import com.mihaelluis.app.customview.LogTextBox.Level;
import com.mihaelluis.app.dummy.DummyContent;
import com.mihaelluis.app.util.VLog;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link MainItemListActivity} in two-pane mode (on tablets) or
 * a {@link ItemDetailActivity} on handsets.
 */
public class ChannelGroupFragment extends Fragment implements OnItemSelectedListener {
	
	private final String TAG = ChannelGroupFragment.class.getSimpleName();
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "ChannelGroup";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;

	private Spinner mGroupListSpinner;

	ServiceManagerContext mSMContext;
	
	IChannelManager mChannelManager;
	
	LogTextBox mLog;
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ChannelGroupFragment() {
	}
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		mSMContext = ServiceManagerContext.getInstance(getActivity().getApplicationContext());
		
		super.onCreate(savedInstanceState);
		
	}

	@SuppressWarnings("static-access")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.channel_group_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		// if (mItem != null) {
		// ((TextView) rootView.findViewById(R.id.item_detail))
		// .setText(mItem.content);
		// }
		
		mChannelManager = IChannelManager.Stub.asInterface(mSMContext.getManager(ServiceManagerContext.TYPE_CHANNEL_MANAGER));
		Log.d(TAG, "Channel Manager is " + mChannelManager==null ? "NULL" : "Not Null");

		mGroupListSpinner = (Spinner) rootView
				.findViewById(R.id.channel_group_list);
		
		mLog = (LogTextBox) rootView.findViewById(R.id.log_text);
		
		VLog.initialize(mLog);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity().getApplicationContext(),
				R.array.dummy_group_list, android.R.layout.simple_spinner_item);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mGroupListSpinner.setAdapter(adapter);
		mGroupListSpinner.setOnItemSelectedListener(this);
		
		try {
			for(String name : mSMContext.listManagers())
			{
				VLog.d(TAG, "Manager name : " + name);
				VLog.e(TAG, "Manager name : " + name);
				VLog.i(TAG, "Manager name : " + name);
				VLog.v(TAG, "Manager name : " + name);
				VLog.w(TAG, "Manager name : " + name);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rootView;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		String groupName = (String) parent.getItemAtPosition(position);
		VLog.d(groupName);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
}

