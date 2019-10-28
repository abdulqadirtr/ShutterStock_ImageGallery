package app.easylink.shuterstockimages.appfeature.base;

import android.os.Bundle;

public interface ViewModel<T extends MvvmView> {

  void attach();

  void attach(T view);

  void resume();

  void pause();

  void detach();

  void restoreInstanceState(Bundle savedInstanceState);

  void saveInstanceState(Bundle outState);
}
