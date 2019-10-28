package app.easylink.shuterstockimages.appfeature.base.navigator;

import android.support.v4.app.FragmentActivity;

public class ActivityNavigator extends Navigator {

  private final FragmentActivity activity;

  public ActivityNavigator(FragmentActivity activity) {
    this.activity = activity;
  }

  @Override public final FragmentActivity getActivity() {
    return activity;
  }
}
