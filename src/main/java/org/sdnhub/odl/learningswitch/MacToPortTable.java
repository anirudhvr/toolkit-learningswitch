
package org.sdnhub.odl.learningswitch;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.opendaylight.controller.sal.utils.HexEncode;
import org.opendaylight.controller.sal.core.Node;
import org.opendaylight.controller.sal.core.NodeConnector;

@XmlRootElement(name="MacToPortTable")
@XmlAccessorType(XmlAccessType.NONE)
public class MacToPortTable {

    // For each switch port, track list of learned MACs
    private Map<NodeConnector, Set<Long>> table;

    public NodeConnector getNodeConnector(Node n, Long mac) {
        for (Map.Entry<NodeConnector, Set<Long>> entry : this.table.entrySet())  {
            if (((NodeConnector)entry.getKey()).getNode().equals(n))
                if (((Set<Long>)entry.getValue()).contains(mac))
                    return (NodeConnector)entry.getKey();
        }
        return null;
    }

    public void setNodeConnector(NodeConnector nc, Long mac) {
        if (table.containsKey(nc)) {
            table.get(nc).add(mac);
        }
        else
            table.put(nc, new HashSet<Long>(Collections.singleton(mac)));
    }

    public void initNodeConnector(NodeConnector nc) {
        //Nothing to do right now
    }

    public void clearNodeConnector(NodeConnector nc) {
        table.remove(nc);
    }

    public void initNode(Node n) {
        //Nothing to do right now
    }

    public void clearNode(Node n) {
        Set<NodeConnector> nodeConnectors = new LinkedHashSet<NodeConnector>();

        for (Map.Entry<NodeConnector, Set<Long>> entry : this.table.entrySet())
            if (((NodeConnector)entry.getKey()).getNode().equals(n))
                nodeConnectors.add((NodeConnector)entry.getKey());

        for (NodeConnector nc: nodeConnectors)
            clearNodeConnector(nc);
    }

    public MacToPortTable() {
        super();
        table = new HashMap<NodeConnector, Set<Long> >();
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

        public MacPortTableElem(String mac, String connector) {
            super();
            this.mac = mac;
            this.connector = connector;
        }
    }

    @XmlElement(name="entries")
    public List< MacPortTableElem >  getMap() {
        NodeConnector nc;
        List< MacPortTableElem > entries = new ArrayList<MacPortTableElem>();

        for (Map.Entry<NodeConnector, Set<Long> > entry : this.table.entrySet()) {
            MacPortTableElem elem = new MacPortTableElem();
            nc = (NodeConnector)entry.getKey();

            for (Long mac: (Set<Long>)entry.getValue()) {
                elem.mac = HexEncode.longToHexString(mac);
                elem.connector = nc.toString();
                entries.add(elem);
            }
        }
        return entries;
    }

//    @XmlElement
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

    public String toString() {
        NodeConnector nc;
        String str = "MAC Table: " + table.size() + " entries\n";
        for (Map.Entry<NodeConnector, Set<Long> > entry : table.entrySet()) {
            nc = (NodeConnector)entry.getKey();
            for (Long mac: (Set<Long>)entry.getValue())
                str += HexEncode.longToHexString(mac) + " - " +
                    nc.toString() + "\n";
        }
        return str;
    }
}

