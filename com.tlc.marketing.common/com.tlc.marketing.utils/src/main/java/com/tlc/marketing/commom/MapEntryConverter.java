package com.tlc.marketing.commom;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.*;

/**
 * Created by chepeiqing on 16/10/9.
 */
public class MapEntryConverter implements Converter {
    public boolean canConvert(Class clazz) {
        return AbstractMap.class.isAssignableFrom(clazz);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        AbstractMap map = (AbstractMap) value;
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext();) {
            String key = (String) it.next();
            Object keyValue = map.get(key);
            if (null == keyValue)
                keyValue = "";
            if (keyValue instanceof ArrayList) {
                ArrayList list = (ArrayList) map.get(key);
                writer.startNode(key);
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    marshal(hm, writer, context);
                }
                writer.endNode();
            } else {
                if (keyValue instanceof HashMap) {
                    writer.startNode(key);
                    marshal((HashMap) keyValue, writer, context);
                    writer.endNode();
                } else {
                    writer.startNode(key);
                    writer.setValue(keyValue.toString());
                    writer.endNode();
                }
            }
        }
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Map<String, String> map = new HashMap<String, String>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            map.put(reader.getNodeName(), reader.getValue());
            reader.moveUp();
        }
        return map;
    }
}
