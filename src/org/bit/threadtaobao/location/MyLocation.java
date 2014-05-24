package org.bit.threadtaobao.location;

import org.bit.threadtaobao.view.R;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKEvent;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocation extends MapActivity {
	static MyLocation myLocation;
	
	//百度MapAPI的管理类
	BMapManager mBMapMan = null;
	
	MapView mMapView = null;
	LocationListener mLocationListener = null;//onResume时注册此listener，onPause时需要Remove
	MyLocationOverlay mLocationOverlay = null;	//定位图层
	
	// 授权Key
	// TODO: 请输入您的Key,
	// 申请地址：http://dev.baidu.com/wiki/static/imap/key/
	String mStrKey = "BD8F93A1E2102B5EF9BF29BE71857224CD998E5D";
	boolean m_bKeyRight = true;	// 授权Key正确，验证通过
	
	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
	static class MyGeneralListener implements MKGeneralListener {
		@Override
		public void onGetNetworkState(int iError) {
			Toast.makeText(MyLocation.myLocation.getApplicationContext(), "您的网络出错啦！",
					Toast.LENGTH_LONG).show();
		}

		@Override
		public void onGetPermissionState(int iError) {
			if (iError ==  MKEvent.ERROR_PERMISSION_DENIED) {
				// 授权Key错误：
				Toast.makeText(MyLocation.myLocation.getApplicationContext(), 
						"请输入正确的授权Key！",
						Toast.LENGTH_LONG).show();
				MyLocation.myLocation.m_bKeyRight = false;
			}
		}
		
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_location);
        mBMapMan = new BMapManager(getApplication());
        mBMapMan.init(this.mStrKey, new MyGeneralListener());
        mBMapMan.start();
        
     // 如果使用地图SDK，请初始化地图Activity
     super.initMapActivity(mBMapMan);
     
     mMapView = (MapView)findViewById(R.id.bmapsView);
     mMapView.setBuiltInZoomControls(true);
     //设置在缩放动画过程中也显示overlay,默认为不绘制
     mMapView.setDrawOverlayWhenZooming(true);
     
	// 添加定位图层
     mLocationOverlay = new MyLocationOverlay(this, mMapView);
		mMapView.getOverlays().add(mLocationOverlay);
		
     // 注册定位事件
     mLocationListener = new LocationListener(){

			@Override
			public void onLocationChanged(Location location) {
				if (location != null){
					GeoPoint pt = new GeoPoint((int)(location.getLatitude()*1e6),
							(int)(location.getLongitude()*1e6));
					mMapView.getController().animateTo(pt);
				}
			}
     };
    }
    @Override
	protected void onPause() {
		mBMapMan.getLocationManager().removeUpdates(mLocationListener);
		mLocationOverlay.disableMyLocation();
        mLocationOverlay.disableCompass(); // 关闭指南针
		mBMapMan.stop();
		super.onPause();
	}
	@Override
	protected void onResume() {
		// 注册定位事件，定位后将地图移动到定位点
        mBMapMan.getLocationManager().requestLocationUpdates(mLocationListener);
        mLocationOverlay.enableMyLocation();
        mLocationOverlay.enableCompass(); // 打开指南针
        mBMapMan.start();
		super.onResume();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
    @Override
    protected void onDestroy() {
        if (mBMapMan != null) {
            mBMapMan.destroy();
            mBMapMan = null;
        }
        super.onDestroy();
    }
}