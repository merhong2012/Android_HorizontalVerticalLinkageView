package com.java.zhw.android_horizontalverticallinkageview.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public abstract class BaseData
        implements Serializable {
    private static final long serialVersionUID = -6519997172926687064L;
    String _id;
    String _keyName;
    String _name;
    protected ArrayList mainDataList;
    private HashMap<String, Object> map;

    protected BaseData() {
        init();
    }


    protected BaseData(String paramString, JSONObject paramJSONObject)
            throws Exception {
        this._keyName = paramString;
        this.mainDataList = new ArrayList();
        init();
        if (paramJSONObject != null)
            parseJSONObject(paramJSONObject);
    }


    protected static String getMethodName(String paramString) {
        String str = "";
        int i = 0;
        int j = 0;
        if (j >= paramString.length())
            return "set" + str;
        char c = paramString.charAt(j);
        if (i != 0) {
            str = str + String.valueOf(c).toUpperCase();
            i = 0;
        }
        while (true) {
            if (j == 0) {
                str = String.valueOf(c).toUpperCase();
                continue;
            }
            if (c == '_') {
                i = 1;
                continue;
            }
            str = str + c;
            j++;
            break;
        }
        return str;
    }

    protected static String getRealClassName(String paramString) {
        String str = "";
        int i = 0;
        int j = 0;
        if (j >= paramString.length())
            return BaseData.class.getPackage().getName() + "." + str + "Data";
        char c = paramString.charAt(j);
        if (i != 0) {
            str = str + new StringBuilder(String.valueOf(c)).toString().toUpperCase();
            i = 0;
        }
        while (true) {

            if (j == 0) {
                str = String.valueOf(c).toUpperCase();
                continue;
            }
            if (c == '_') {
                i = 1;
                continue;
            }
            str = str + c;
            j++;
            break;
        }
        return str;
    }

    public static Date stringToDate(String paramString) {
        if ((paramString != null) && (paramString.length() > 0))
            try {
                int i = paramString.lastIndexOf(":");
                String str = paramString.substring(0, i) + paramString.substring(i + 1,
                        paramString.length());
                Date localDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss Z").parse(str);
                return localDate;
            } catch (Exception localException) {
            }
        return null;
    }

    public void addData(String paramString, BaseData paramBaseData) {
        setAbstractValue(paramString, paramBaseData);
    }

    public void clearObject() {
        if (this.map != null)
            this.map.clear();
    }

    protected BaseData createObject(String paramString, Object paramObject)
            throws Exception {
        if (paramString != null)
            paramString = paramString.trim();
        String str = getRealClassName(paramString);
        try {
            Class localClass = Class.forName(str);
            BaseData localBaseData = null;
            if (localClass != null) {
                Class[] arrayOfClass = new Class[2];
                arrayOfClass[0] = String.class;
                arrayOfClass[1] = paramObject.getClass();
                localBaseData = (BaseData) localClass.getConstructor(arrayOfClass).newInstance
                        (new Object[]{paramString, paramObject});
            }
            return localBaseData;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    public BaseData getData(int paramInt) {
        if (this.mainDataList.size() > paramInt)
            return (BaseData) this.mainDataList.get(paramInt);
        return null;
    }

    public BaseData getData(Class paramClass) {
        BaseData localBaseData;
        if (this.mainDataList == null) {
            return null;
        }
        for (int i = 0; ; i++) {
            if (i >= this.mainDataList.size())
                return null;
            localBaseData = (BaseData) this.mainDataList.get(i);
            if (localBaseData.getClass() == paramClass)
                break;
        }
        return localBaseData;
    }

    public BaseData getData(String paramString) {
        if (paramString != null) ;
        for (int i = 0; ; i++) {
            BaseData localBaseData = null;
            if (i >= this.mainDataList.size())
                localBaseData = null;
            do {
                return localBaseData;
            }
            while (paramString.equalsIgnoreCase(localBaseData.getKeyName()));
        }
    }

    public String getId() {
        return this._id;
    }

    public String getKeyName() {
        return this._keyName;
    }

    public String getName() {
        return this._name;
    }

    public Object getObject(String paramString) {
        if (this.map != null)
            return this.map.get(paramString);
        return null;
    }

    public int getObjectCount() {
        if (this.map != null)
            return this.map.size();
        return 0;
    }

    public int getSize() {
        if (this.mainDataList == null)
            return 0;
        return this.mainDataList.size();
    }

    public String getString(String paramString) {
        if (this.map != null) {
            Object localObject = this.map.get(paramString);
            if (localObject != null)
                return localObject.toString();
        }
        return null;
    }

    protected void init() {

    }

    protected void parseJSONArray(JSONArray paramJSONArray) {
        int i = 0;
        while (true) {
            if (i >= paramJSONArray.length())
                return;
            try {
                Object localObject = paramJSONArray.get(i);
                if ((localObject instanceof String)) {
                    setStringValue(null, (String) localObject);
                } else if ((localObject instanceof JSONArray)) {
                    setJSONArrayValue(null, (JSONArray) localObject);
                } else if ((localObject instanceof JSONObject)) {
                    setJSONObjectValue(null, (JSONObject) localObject);
                } else if ((localObject instanceof Number)) {
                    setNumberValue(null, (Number) localObject);
                    setStringValue(null, (String) localObject);
                }
                i++;
            } catch (Exception localException) {
                break;
            }
        }
    }

    protected void parseJSONObject(JSONObject paramJSONObject)
            throws Exception {
        Iterator localIterator = paramJSONObject.keys();
        while (true) {
            if (!localIterator.hasNext())
                return;
            String str = (String) localIterator.next();
            Object localObject = paramJSONObject.get(str);
            if ((localObject instanceof String)) {
                setStringValue(str, (String) localObject);
                continue;
            }
            if ((localObject instanceof JSONArray)) {
                setJSONArrayValue(str, (JSONArray) localObject);
                continue;
            }
            if ((localObject instanceof JSONObject)) {
                setJSONObjectValue(str, (JSONObject) localObject);
                continue;
            }
            if ((localObject instanceof Number)) {
                setNumberValue(str, (Number) localObject);
                continue;
            }
            if ((localObject instanceof Boolean)) {
                setBooleanValue(str, (Boolean) localObject);
                continue;
            }
            setStringValue(str, localObject.toString());
        }
    }

    protected boolean setAbstractValue(String paramString, BaseData paramBaseData) {
        String str = getMethodName(paramString);
        try {
            Class localClass = getClass();
            Class[] arrayOfClass = new Class[1];
            arrayOfClass[0] = paramBaseData.getClass();
            localClass.getMethod(str, arrayOfClass).invoke(this, new Object[]{paramBaseData});
            this.mainDataList.add(paramBaseData);
            return true;
        } catch (Exception localException) {
            this.mainDataList.add(paramBaseData);
        }
        return false;
    }

    protected boolean setBooleanValue(String paramString, Boolean paramBoolean) {
        String str = getMethodName(paramString);
        try {
            getClass().getMethod(str, new Class[]{Number.class}).invoke(this, new
                    Object[]{paramBoolean});
            return true;
        } catch (NoSuchMethodException localNoSuchMethodException) {
            setObject(paramString, paramBoolean);
            return true;
        } catch (Exception localException) {
        }
        return false;
    }

    public void setData(int paramInt, BaseData paramBaseData) {
        this.mainDataList.set(paramInt, paramBaseData);
    }

    public void setId(String paramString) {
        this._id = paramString;
    }

    protected void setJSONArrayValue(String paramString, JSONArray paramJSONArray)
            throws Exception {
        Object localObject = createObject(paramString, paramJSONArray);
        setAbstractValue(paramString, (BaseData) localObject);
    }

    protected BaseData setJSONObjectValue(String paramString, JSONObject paramJSONObject)
            throws Exception {
        Object localObject = createObject(paramString, paramJSONObject);
        setAbstractValue(paramString, (BaseData) localObject);
        return (BaseData) localObject;
    }

    public void setName(String paramString) {
        this._name = paramString;
    }

    protected boolean setNumberValue(String paramString, Number paramNumber) {
        String str = getMethodName(paramString);
        try {
            getClass().getMethod(str, new Class[]{Number.class}).invoke(this, new
                    Object[]{paramNumber});
            return true;
        } catch (NoSuchMethodException localNoSuchMethodException) {
            setObject(paramString, paramNumber);
            return true;
        } catch (Exception localException) {
        }
        return false;
    }

    public void setObject(String paramString, Object paramObject) {
        if (this.map == null)
            this.map = new HashMap();
        if ((paramString == null) || (paramString.length() == 0))
            paramString = String.valueOf(1 + this.map.size());
        this.map.put(paramString, paramObject);
    }

    protected boolean setObjectValue(String paramString, Object paramObject) {
        String str = getMethodName(paramString);
        try {
            Class localClass = getClass();
            Class[] arrayOfClass = new Class[1];
            arrayOfClass[0] = paramObject.getClass();
            localClass.getMethod(str, arrayOfClass).invoke(this, new Object[]{paramObject});
            return true;
        } catch (NoSuchMethodException localNoSuchMethodException) {
            setObject(paramString, paramObject);
            return true;
        } catch (Exception localException) {
        }
        return false;
    }

    protected boolean setStringValue(Class paramClass, String paramString1, String paramString2) {
        String str = getMethodName(paramString1);
        try {
            Class[] classes = {String.class};
            paramClass.getMethods();
            Method localMethod = paramClass.getMethod(str, classes);
            if (localMethod != null) {
                localMethod.invoke(this, new Object[]{paramString2});
                return true;
            }
            return false;
        } catch (Exception localException) {
        }
        return false;
    }

    protected boolean setStringValue(String paramString1, String paramString2) {
        try {
            String str = getMethodName(paramString1);
            Class localClass = getClass();
            Class[] classes = {String.class};
            localClass.getMethods();
            Method localMethod = localClass.getMethod(str, classes);
            int i = 0;
            if (localMethod != null) {
                localMethod.invoke(this, new Object[]{paramString2});
                i = 1;
            }
            if (i == 0)
                setObject(paramString1, paramString2);
            return true;
        } catch (Exception localException) {
            setObject(paramString1, paramString2);
        }
        return false;
    }
}