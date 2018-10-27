/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

'use strict';

/* global getParticipantRegistry emit */

/**
 * EarnCredit transaction
 * @param {org.pikachu2.biznet.EarnCredit} earncredit
 * @transaction
 */
async function EarnCredit(earncredit) {

  //update member nowcredit
  earncredit.member.Info.nowCredit = earncredit.member.Info.nowCredit + earncredit.nowCredit;

  //update member
  const memberRegistry = await getParticipantRegistry('org.pikachu2.biznet.Member');
  await memberRegistry.update(earncredit.member);

  // check if company exists on the network
  const idRegistry = await getParticipantRegistry('org.pikachu2.biznet.Company');
  idExists = await idRegistry.exists(earncredit.company.id);
  if (idExists == false) {
    throw new Error('Company does not exist - check company id');
  }
}

 /**
 * loseCredit transaction
 * @param {org.pikachu2.biznet.LoseCredit} loseCredit
 * @transaction
 */
async function LoseCredit(loseCredit) {
    //update memberID nowCredit
    loseCredit.memberID.nowCredit = loseCredit.memberID.nowCredit - loseCredit.nowCredit
  
    //update memberID
    const memberRegistry = await getParticipantRegistry('org.pikachu2.biznet.member');
    await memberRegistry.update(loseCredit.memberID);
  
    // check if company exists on the network
    const idRegistry = await getParticipantRegistry('org.pikachu2.biznet.company');
    idExists = await idRegistry.exists(loseCredit.company.id);
    if (idExists == false) {
      throw new Error('companyID does not exist - check company id');
    }
  }
  
  /**
 * UpdateHistory transaction
 * @param {org.pikachu2.biznet.UpdateHistory} updatehistory
 * @transaction
 */
async function UpdateHistory(updatehistory) {
    //let currParticipant = getCurrentParticipant();
    // Get the nowcredit.
    const toUpdateHistory=await getParticipantRegistry('org.pikachu2.biznet.Member');
    updatehistory.member.Info.historyInfo.credit = toUpdateHistory.Info.nowCredit;
    //get time 
    let date=new Date();
    updatehistory.historyInfo.createtime=date.toLocaleTimeString();
    await historyInfo.push(updatehistory.historyInfo);
}