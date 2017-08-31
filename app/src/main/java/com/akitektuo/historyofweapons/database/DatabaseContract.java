package com.akitektuo.historyofweapons.database;

import android.provider.BaseColumns;

/**
 * Created by Akitektuo on 14.12.2016.
 */

class DatabaseContract {

    abstract class QuestionContractEntry implements BaseColumns {

        static final String TABLE_NAME = "question";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_IMAGE_DATA = "imageData";
        static final String COLUMN_NAME_QUESTION_EN = "questionEn";
        static final String COLUMN_NAME_QUESTION_RO = "questionRo";
        static final String COLUMN_NAME_VARIANTS_EN = "variantsEn";
        static final String COLUMN_NAME_VARIANTS_RO = "variantsRo";
        static final String COLUMN_NAME_ANSWER = "answer";

    }

    abstract class WeaponContractEntry implements BaseColumns {

        static final String TABLE_NAME = "weapon";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_IMAGE_DATA = "imageDate";
        static final String COLUMN_NAME_DESCRIPTION_EN = "descriptionEn";
        static final String COLUMN_NAME_DESCRIPTION_RO = "descriptionRo";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_TYPE = "type";

    }

}
