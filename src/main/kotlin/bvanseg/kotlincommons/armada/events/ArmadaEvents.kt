/*
 * MIT License
 *
 * Copyright (c) 2019 Boston Vanseghi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package bvanseg.kotlincommons.armada.events

import bvanseg.kotlincommons.armada.CommandManager
import bvanseg.kotlincommons.armada.commands.BaseCommand
import bvanseg.kotlincommons.armada.commands.InternalCommand
import bvanseg.kotlincommons.armada.contexts.Context
import bvanseg.kotlincommons.armada.gears.Gear
import bvanseg.kotlincommons.armada.transformers.Transformer

class Init : ArmadaEvent()

open class CommandEvent(val manager: CommandManager<*>) : ArmadaEvent()
open class CommandExecuteEvent(manager: CommandManager<*>, val command: InternalCommand, val context: Context) :
    CommandEvent(manager) {
    class Pre(manager: CommandManager<*>, command: InternalCommand, context: Context) :
        CommandExecuteEvent(manager, command, context)
    class Post(manager: CommandManager<*>, command: InternalCommand, context: Context, val result: Any?) :
        CommandExecuteEvent(manager, command, context)
}

open class CommandAddEvent(val command: BaseCommand, manager: CommandManager<*>) : CommandEvent(manager) {
    class Pre(command: BaseCommand, manager: CommandManager<*>) : CommandAddEvent(command, manager)
    class Post(command: BaseCommand, manager: CommandManager<*>) : CommandAddEvent(command, manager)
}

open class GearEvent(val gear: Gear, val manager: CommandManager<*>) : ArmadaEvent()
open class GearAddEvent(gear: Gear, manager: CommandManager<*>) : GearEvent(gear, manager) {
    class Pre(gear: Gear, manager: CommandManager<*>) : GearAddEvent(gear, manager)
    class Post(gear: Gear, manager: CommandManager<*>) : GearAddEvent(gear, manager)
}

open class TransformerEvent(val transformer: Transformer<*>, val manager: CommandManager<*>) : ArmadaEvent()
open class TransformerAddEvent(transformer: Transformer<*>, manager: CommandManager<*>) :
    TransformerEvent(transformer, manager) {
    class Pre(transformer: Transformer<*>, manager: CommandManager<*>) : TransformerAddEvent(transformer, manager)
    class Post(transformer: Transformer<*>, manager: CommandManager<*>) : TransformerAddEvent(transformer, manager)
}
