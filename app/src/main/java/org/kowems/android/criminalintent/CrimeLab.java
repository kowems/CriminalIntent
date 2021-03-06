package org.kowems.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.kowems.android.criminalintent.database.CrimeBaseHelper;
import org.kowems.android.criminalintent.database.CrimeDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Eric Ju on 2016/8/14.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME,null,values);
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>();
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbSchema.CrimeTable.NAME,values, CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",new String[]{uuidString});
    }

    public Crime getCrime(UUID uuid) {

        return null;
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.CrimeTable.Cols.UUID,crime.getId().toString());
        values.put(CrimeDbSchema.CrimeTable.Cols.TITLE,crime.getTitle());
        values.put(CrimeDbSchema.CrimeTable.Cols.DATE,crime.getDate().getTime());
        values.put(CrimeDbSchema.CrimeTable.Cols.SOVLED,crime.isSolved()?1:0);

        return values;
    }

    private Cursor queryCrimes(String whereClause,String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return cursor;
    }
}
