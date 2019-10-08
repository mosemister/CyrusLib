package org.cyrus.classhandler.common.line.statement;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.exception.StatementAppendException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Statement extends Writable {

    interface InLine<X extends CommonClass> extends Statement, Line<X> {

        /**
         * Gets the required classes for the statement, if a requirement is
         * optional, it will still show in here
         * @return All requirements for the statement
         */
        List<CommonClass<? extends CommonClass>> getStatementRequirements();

        /**
         * Gets the lines that fill in the statements requirements
         * @return All lines to fill the statement
         */
        List<Line<X>> getStatementParameters();

        /**
         * Statements can not return a value
         * @return optional empty
         */
        @Deprecated
        @Override
        default Optional<CommonClass<? extends CommonClass>> getReturn() {
            return Optional.empty();
        }

        interface Attachable<X extends CommonClass> extends InLine<X>{

            /**
             * Gets the attached statements in a unmodifiable list
             * @return All attached statements
             */
            List<Statement> getAttached();

            /**
             * Adds a collection of statement to the bottom of this
             * @param statements the statements to attach
             * @return itself for chaining
             * @throws IOException All statements that could not be attached and why
             */
            Attachable<X> addStatements(Collection<Statement> statements) throws StatementAppendException;

            /**
             * Removes a collection of statements
             * @param statements the statements to remove
             * @return itself for chaining
             * @throws IOException All statements that can not be removed and why
             */
            Attachable<X> removeStatements(Collection<Statement> statements) throws StatementAppendException;

            /**
             * Adds a array of statements to the bottom of this
             * @param statements the statements to attach
             * @return itself for chaining
             * @throws IOException All statements that could not be attached and why
             */
            default Attachable<X> addStatements(Statement... statements) throws StatementAppendException {
                return this.addStatements(Arrays.asList(statements));
            }

            /**
             * Removes a collection of statements
             * @param statements the statements to remove
             * @return itself for chaining
             * @throws IOException all statements tat can not be removed and why
             */
            default Attachable<X> removeStatements(Statement... statements) throws StatementAppendException {
                return this.removeStatements(Arrays.asList(statements));
            }

        }

    }

    /**
     * gets the lines within the statement
     * @return All the lines within the statement
     */
    List<Line<? extends CommonClass>> getLines();
}
