package org.kodigo.mini_stock_sytem.model;

/** Inventory movement types. */
public enum MovementType {
    IN,      // incoming stock
    OUT,     // outgoing stock (sale/consumption)
    ADJUST   // manual adjustment (loss, audit, correction)
}