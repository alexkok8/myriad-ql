/**
 * Created by Manuel on 13/02/2017.
 */

let Statement = require('./Statement.js');

module.exports = class IfStatement extends Statement {

    constructor(options = {}) {
        super();
        this.condition = options.condition;
        this.body = options.body;
    }
}