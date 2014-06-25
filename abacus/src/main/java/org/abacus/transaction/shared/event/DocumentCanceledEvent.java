package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class DocumentCanceledEvent<T extends TraDocumentEntity> extends UpdatedEvent {

}
