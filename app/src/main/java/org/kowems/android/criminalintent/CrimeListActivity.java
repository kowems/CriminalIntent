package org.kowems.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Eric Ju on 2016/8/14.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}