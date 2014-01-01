package org.dyndns.warenix.hkgcp.test;

import org.dyndns.warenix.hkg.provider.HKGMetaData;
import org.dyndns.warenix.hkg.provider.HKGProvider;

public class SimpleProviderTestCase2 extends ProviderTestCase2<HKGProvider> {

	private static final String TAG = "SimpleProviderTestCase2";

	public SimpleProviderTestCase2() {
		super(HKGProvider.class, HKGProvider.class.getName());
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	// public void testInsert() {
	// ContentProvider provider = getProvider();
	//
	// Uri uri = SimpleColumns.getContentUri(new SimpleDBConfig());
	//
	// ContentValues values = new ContentValues();
	// values.put(SimpleColumns.KEY, "title 1");
	// values.put(SimpleColumns.VALUE, "text 1");
	// Uri insertUri = provider.insert(uri, values);
	// Log.d(TAG, "inserted uri:" + insertUri);
	// assertNotNull(insertUri);
	// }

	// public void testQueryForumByPage() {
	// ContentProvider provider = getProvider();
	//
	// Uri uri = Uri.parse("content://" + HKGMetaData.AUTHORITY
	// + "/forum/GM/1");
	//
	// Cursor cursor = provider.query(uri, null, null, null, null);
	//
	// assertNotNull("can't resolve uri:" + uri, cursor);
	// Log.d(TAG, "rows count:" + cursor.getCount());
	// try {
	// cursor = provider.query(uri, null, null, null, null);
	// while (cursor.moveToNext()) {
	// for (int i = 0; i < cursor.getColumnCount(); ++i) {
	// Log.d(TAG, i + "->" + cursor.getColumnName(i) + ":"
	// + cursor.getString(i));
	// }
	// }
	// } catch (IllegalArgumentException e) {
	// assertTrue(false);
	// }
	// }

	// public void testQueryHKGThreadByPage() {
	// ContentProvider provider = getProvider();
	//
	// Uri uri = Uri.parse("content://" + HKGMetaData.AUTHORITY
	// + "/thread/4037502/1");
	//
	// Cursor cursor = provider.query(uri, null, null, null, null);
	//
	// assertNotNull("can't resolve uri:" + uri, cursor);
	// Log.d(TAG, "rows count:" + cursor.getCount());
	// assertTrue("at least 1 reply count. get " + cursor.getCount(),
	// cursor.getCount() > 0);
	// try {
	// cursor = provider.query(uri, null, null, null, null);
	// while (cursor.moveToNext()) {
	// for (int i = 0; i < cursor.getColumnCount(); ++i) {
	// Log.d(TAG, i + "->" + cursor.getColumnName(i) + ":"
	// + cursor.getString(i));
	// }
	// }
	// } catch (IllegalArgumentException e) {
	// assertTrue(false);
	// }
	// }

	// public void testHKGForum() {
	// ContentProvider provider = getProvider();
	//
	// Uri uri = HKGMetaData.getUriListForum();
	//
	// Cursor cursor = provider.query(uri, null, null, null, null);
	//
	// assertNotNull("can't resolve uri:" + uri, cursor);
	// Log.d(TAG, "rows count:" + cursor.getCount());
	// assertTrue("at least 1 reply count. get " + cursor.getCount(),
	// cursor.getCount() > 0);
	// try {
	// cursor = provider.query(uri, null, null, null, null);
	// while (cursor.moveToNext()) {
	// for (int i = 0; i < cursor.getColumnCount(); ++i) {
	// Log.d(TAG, i + "->" + cursor.getColumnName(i) + ":"
	// + cursor.getString(i));
	// }
	// }
	// } catch (IllegalArgumentException e) {
	// assertTrue(false);
	// }
	// }

	// public void testDelete() {
	// ContentProvider provider = getProvider();
	// Uri uri = SimpleColumns.getContentUri(new SimpleDBConfig());
	//
	// int rowsAffected = provider.delete(uri, "_id=1", null);
	// assertTrue(rowsAffected > 0);
	// }
	//
	// public void testUpdate() {
	// ContentProvider provider = getProvider();
	// // Uri uri = SimpleColumns.getContentUri(new CustomDBConfig());
	// Uri uri = Uri.parse("content://" + MyContentProvider.AUTHORITY + "/"
	// + SimpleDB.SIMPLE_TABLE_NAME);
	// ContentValues values = new ContentValues();
	// values.put(SimpleColumns.VALUE, "updated");
	// int rowsAffected = provider.update(uri, values, "_id=1", null);
	// assertTrue(rowsAffected > 0);
	//
	// testQuery();
	// }
	//
	// class CustomDBConfig extends SimpleDBConfig {
	// public String getDatabaseName() {
	// return "custom.db";
	// }
	//
	// }

	public void testLastVisitPage() {
		ContentProvider provider = getProvider();

		// test data
		String threadId = "456";
		int pageNo = 4;

		// insert
		Uri uri = HKGMetaData.getUriShowLastVisitThreadPage(threadId, pageNo);
		//
		ContentValues values = new ContentValues();
		values.put(HKGMetaData.HKGThreadLastVisitColumns.threadId, threadId);
		values.put(HKGMetaData.HKGThreadLastVisitColumns.pageNo, pageNo);

		Uri insertUri = provider.insert(uri, values);
		Log.d(TAG, "inserted uri:" + insertUri);
		assertNotNull(insertUri);

		Cursor cursor = provider.query(uri, null, null, null, null);

		assertNotNull("can't resolve uri:" + uri, cursor);
		Log.d(TAG, "rows count:" + cursor.getCount());

		try {
			cursor = provider.query(uri, null, null, null, null);
			while (cursor.moveToNext()) {
				printCursorRow(cursor);
			}
		} catch (IllegalArgumentException e) {
			assertTrue(false);
		}

		// update
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		values.put(HKGMetaData.HKGThreadLastVisitColumns.pageNo, pageNo + 100);
		insertUri = provider.insert(uri, values);

		// String selection = String.format("%s=%s",
		// HKGMetaData.HKGThreadLastVisitColumns.threadId, threadId);
		// int rows = provider.update(uri, values, selection, null);
		// assertTrue("should update least one row", rows > 0);

		try {
			cursor = provider.query(uri, null, null, null, null);
			while (cursor.moveToNext()) {
				printCursorRow(cursor);
			}
		} catch (IllegalArgumentException e) {
			assertTrue(false);
		}

	}

	private void printCursorRow(Cursor cursor) {
		for (int i = 0; i < cursor.getColumnCount(); ++i) {
			Log.d(TAG,
					i + "->" + cursor.getColumnName(i) + ":"
							+ cursor.getString(i));
		}
	}
}
