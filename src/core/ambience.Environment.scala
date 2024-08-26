/*
    Ambience, version [unreleased]. Copyright 2024 Jon Pretty, Propensive OÜ.

    The primary distribution site is: https://propensive.com/

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
    file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the
    License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
    either express or implied. See the License for the specific language governing permissions
    and limitations under the License.
*/

package ambience

import language.experimental.captureChecking
import language.dynamics

import anticipation.*
import contingency.*
import fulminate.*
import rudiments.*
import vacuous.*

@capability
trait Environment:
  def variable(name: Text): Optional[Text]
  def knownVariables: Set[Text] = Set()

object Environment extends Dynamic:
  given default(using Quickstart): Environment = environments.virtualMachine

  def apply[VariableType](variable: Text)
      (using environment:      Environment,
             reader:           EnvironmentVariable[Label, VariableType],
             environmentError: Tactic[EnvironmentError])
          : VariableType^{environment, reader, environmentError} =

    environment.variable(variable).let(reader.read).or(raise(EnvironmentError(variable), reader.read(Text(""))))

  inline def selectDynamic[VariableType](key: String)
      (using environment:      Environment,
             reader:           EnvironmentVariable[key.type, VariableType],
             environmentError: Tactic[EnvironmentError])
          : VariableType^{environment, reader, environmentError} =

    environment.variable(reader.defaultName).let(reader.read(_)).or:
      raise(EnvironmentError(reader.defaultName), reader.read(Text("")))