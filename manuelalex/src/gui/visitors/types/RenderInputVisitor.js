/**
 * Created by Manuel on 24/04/2017.
 */

import {Checkbox} from 'arva-kit/components/Checkbox.js';
import {SingleLineTextInput} from 'arva-kit/input/SingleLineTextInput.js';

export class RenderInputVisitor {

    renderBoolean(qlBoolean) {
        const renderable = new Checkbox({
            state: false,
            enabled: true
        });

        renderable.on('unchecked', () => {
            renderable._eventOutput.emit('state', { value: false, type: qlBoolean });
        });
        renderable.on('checked', () => {
            renderable._eventOutput.emit('state', { value: true, type: qlBoolean });
        });
        renderable.setState = (state) => {
            state !== undefined && renderable.setChecked(state);
        };

        return renderable;
    }

    renderString(qlString) {
        const renderable = new SingleLineTextInput({});
        renderable.on('message', (message) => {
            renderable._eventOutput.emit('state', { value: message, type: qlString });
        });
        renderable.setState = (state) => {
            state !== undefined && renderable.setValue(state);
        };
        return renderable;
    }

    renderDate(qlData) {
        const renderable = new SingleLineTextInput({
            inputOptions: { type: 'date' }
        });
        renderable.on('message', (message) => {
            renderable._eventOutput.emit('state', { value: message, type: qlData });
        });
        renderable.setState = (state) => {
            state !== undefined && renderable.setValue(state);
        };
        return renderable;
    }

    renderNumber(qlNumber) {
        const renderable = new SingleLineTextInput({
            inputOptions: { type: 'number' }
        });
        renderable.on('message', (message) => {
            renderable._eventOutput.emit('state', { value: message, type: qlNumber });
        });
        renderable.setState = (state) => {
            state !== undefined && renderable.setValue(state);
        };
        return renderable;
    }

    renderMoney(qlMoney) {
        const renderable = new SingleLineTextInput({
            inputOptions: { type: 'number' }
        });
        renderable.on('message', (message) => {
            renderable._eventOutput.emit('state', { value: message, type: qlMoney });
        });
        renderable.setState = (state) => {
            state !== undefined && renderable.setValue(state);
        };
        return renderable;
    }
}