package com.ali.reflectdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.greendaotest.database.DaoSession;
import com.example.greendaotest.database.PersonDao;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private DaoSession daoSession;
    private PersonDao personDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        daoSession = DaoManager.getDefault(this).daoSession();
        personDao = daoSession.getPersonDao();
        for (int i = 0; i < 10; i++) {
            personDao.insert(new Person(null, "wang" + i, i));
        }
    }

    public void onClick(View view) {


        List<Person> list = personDao.queryBuilder().build()
                .list();

        fore(list);
        Person unique = personDao.queryBuilder()
                .where(PersonDao.Properties.Id.eq(2))
                .build().unique();
        unique.setName("tao");
        personDao.update(unique);


        List<Person> list1 = personDao.queryBuilder()
                .where(PersonDao.Properties.Age.gt(10),PersonDao.Properties.Id.lt(18))
                .build()
                .list();
        fore(list1);

        personDao.deleteByKey(unique.getId());

    }

    public void fore(List<Person> list) {
        for (Person person : list) {
            Log.e("tag",person.toString());
        }

    }

}
