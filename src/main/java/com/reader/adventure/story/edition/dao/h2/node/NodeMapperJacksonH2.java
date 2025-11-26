package com.reader.adventure.story.edition.dao.h2.node;

import com.reader.adventure.story.edition.dao.h2.choice.AChoiceH2;
import com.reader.adventure.story.edition.dao.h2.choice.ChoiceConditionalH2;
import com.reader.adventure.story.edition.dao.h2.choice.ChoiceDirectH2;
import com.reader.adventure.story.edition.dao.h2.choice.ChoiceMapperJacksonH2;
import com.reader.adventure.story.read.dao.Jackson.choice.ChoiceConditionalJackson;
import com.reader.adventure.story.read.dao.Jackson.choice.ChoiceDirectJackson;
import com.reader.adventure.story.read.dao.Jackson.choice.IChoiceJackson;
import com.reader.adventure.story.read.dao.Jackson.node.NodeJackson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Mapper(uses = {ChoiceMapperJacksonH2.class})
public interface NodeMapperJacksonH2 {
    NodeMapperJacksonH2 INSTANCE = Mappers.getMapper(NodeMapperJacksonH2.class);

    @Mapping(target = "story", ignore = true)
    NodeH2 sourceToTarget(NodeJackson node);

    default Map<String, NodeH2> sourceToTarget(Map<String, NodeJackson> nodes) {
        if (nodes == null) {
            return Collections.emptyMap();
        }
        Map<String, NodeH2> result = new HashMap<>();
        for (Map.Entry<String, NodeJackson> entry : nodes.entrySet()) {
            NodeH2 node = sourceToTarget(entry.getValue());
            result.put(node.getId(), node);
        }
        return result;
    }

    default void linkNextNodes(Map<String, NodeJackson> nodeJacksonMap, Map<String, NodeH2> nodeMap) {
        for (Map.Entry<String, NodeJackson> entry : nodeJacksonMap.entrySet()) {
            NodeJackson nodeJackson = entry.getValue();
            NodeH2 node = nodeMap.get(entry.getKey());

            if (nodeJackson.choice() != null) {
                for (int i = 0; i < nodeJackson.choice().size(); i++) {
                    IChoiceJackson choice = nodeJackson.choice().get(i);
                    AChoiceH2 choiceH2 = node.getChoice().get(i);
                    if (choiceH2 instanceof ChoiceDirectH2) {
                        choiceH2.setNextNode(nodeMap.get(((ChoiceDirectJackson) choice).next()));
                    } else if (choiceH2 instanceof ChoiceConditionalH2) {
                        choiceH2.setNextNode(nodeMap.get(((ChoiceConditionalJackson) choice).next()));
                        ((ChoiceConditionalH2) choiceH2).setNextFail(nodeMap.get(((ChoiceConditionalJackson) choice).nextFail()));
                    }
                    choiceH2.setNode(node);
                }
            }
        }
    }
}