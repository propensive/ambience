/*
    Ambience, version 0.24.0. Copyright 2025 Jon Pretty, Propensive OÜ.

    The primary distribution site is: https://propensive.com/

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
    file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the
    License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
    either express or implied. See the License for the specific language governing permissions
    and limitations under the License.
*/

package rudiments

import language.experimental.pureFunctions

import ambience.*
import anticipation.*
import contingency.*

package workingDirectories:
  given (using Tactic[SystemPropertyError], SystemProperties)
      => WorkingDirectory as virtualMachine =
    () => Properties.user.dir[Text]()

package homeDirectories:
  given (using Tactic[SystemPropertyError], SystemProperties) => HomeDirectory as virtualMachine =
    () => Properties.user.home[Text]()
