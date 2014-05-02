
package org.sdnhub.learningswitch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.opendaylight.controller.sal.utils.HexEncode;


import org.opendaylight.controller.sal.core.NodeConnector;

@XmlRootElement(name="MacToPortTable")
@XmlAccessorType(XmlAccessType.NONE)
public class MacToPortTable {
    
	public Map<Long, NodeConnector> table;
	
	public NodeConnector getNodeConnector(Long mac) {
		return table.get(mac);
	}
	
	public NodeConnector setNodeConnector(Long mac, NodeConnector nc) {
		return table.put(mac, nc);
	}
	public MacToPortTable() {
		super();
		table = new HashMap<Long, NodeConnector>();
	}
	
	public class MacPortTableElem {
		@XmlElement
		String mac;
		@XmlElement
		String connector;
		public MacPortTableElem() {
			// TODO Auto-generated constructor stub
			super();
		}
	}
	
	@XmlElement(name="entries")
	public List< MacPortTableElem >  getMap() {
		List< MacPortTableElem > entries = new ArrayList<MacPortTableElem>();
		for (Map.Entry entry : this.table.entrySet()) {
			MacPortTableElem elem = new MacPortTableElem();

			elem.mac = HexEncode.longToHexString((Long)entry.getKey());
			elem.connector = entry.getValue().toString();
			entries.add(elem);		
		}
		return entries;
	}
	
//	@XmlElement
//    private String uuid;
//    @XmlElement
//    private String foo;
//    @XmlElement
//    private String bar;
//
//    public String getUuid() {
//        return uuid;
//    }
//    public String getFoo() {
//        return foo;
//    }
//    public String getBar() {
//        return bar;
//    }
//    public MacToPortTable() {
//        super();
//    }
//    public MacToPortTable(String uuid, String foo, String bar) {
//        super();
//        this.uuid = uuid;
//        this.foo = foo;
//        this.bar = bar;
//    }
//    
    
}

