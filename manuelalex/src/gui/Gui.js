/**
 * Created by Manuel on 25/02/2017.
 */

import {Router} from 'arva-js/core/Router.js';
import {Injection} from 'arva-js/utils/Injection.js';
import {Program} from './Program.js';
import {RenderVisitor} from './renderers/RenderVisitor.js';
import {EvaluationVisitor} from './EvaluationVisitor.js';
import {RenderInputVisitor} from './renderers/types/RenderInputVisitor.js';
import {RenderValueVisitor} from './renderers/types/RenderValueVisitor.js';

export class GUI {

    program;
    ast;

    constructor(ast = {}, memoryState = {}) {
        this.ast = ast;
        this.memoryState = memoryState;
    }

    async createGUI(ast = this.ast) {
        this.application = new Program(ast.getProgram());
        await this.application.start();

        this.renderGUI(this.application, this.ast, this.memoryState);
        this.memoryState.on('set', this.renderGUI.bind(this, this.application, this.ast, this.memoryState));
    }

    renderGUI(program = {}, ast = {}, memoryState = {}) {

        const view = program.createView();
        program.setViewForControllerMethod('QL', 'Index', view);

        const evaluationVisitor = new EvaluationVisitor();
        const renderInputVisitor = new RenderInputVisitor();
        const renderValueVisitor = new RenderValueVisitor();
        const renderVisitor = new RenderVisitor(memoryState, evaluationVisitor, renderValueVisitor, renderInputVisitor);
        renderVisitor.visitProgram(ast.getProgram(), view);

        const router = Injection.get(Router);
        router.go('QL', 'Index');
    }

    showValidationErrors(errors) {
        document.body.innerHTML = 'The following errors have occurred during validation:';

        document.body.innerHTML = document.body.innerHTML + '<ul>';
        for (const errorStatement of errors) {
            document.body.innerHTML = document.body.innerHTML + '<li>' + errorStatement + '</li>';
        }
        document.body.innerHTML = document.body.innerHTML + '</ul>';

    }


    showParserErrors(parseString, errors) {
        document.body.innerHTML = 'The following errors have occurred during parsing:';

        parseString = parseString.replace(/\n/g, '<br>');
        for (const error of errors) {
            document.body.innerHTML = `${document.body.innerHTML} <br> <div ="error"> ${error}</div>`;
        }
        document.body.innerHTML = `${document.body.innerHTML} <br> <div>${parseString}</div>`;
    }
}
