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
 * userEarnCredit transaction
 * @param {org.pikachu2.biznet.userEarnCredit} userearncredit
 * @transaction
 */
async function userEarnCredit(userearncredit) {
  //update member nowcredit
  userearncredit.member.Info.Credit = userearncredit.member.Info.Credit + userearncredit.Credit;
  //update member
  const memberRegistry = await getParticipantRegistry('org.pikachu2.biznet.Member');
  await memberRegistry.update(userearncredit.member);
}
 /**
 * userloseCredit transaction
 * @param {org.pikachu2.biznet.userLoseCredit} userloseCredit
 * @transaction
 */
async function userLoseCredit(userloseCredit) {
    //update memberID nowCredit
    userloseCredit.member.Info.Credit = userloseCredit.member.Credit - userloseCredit.Credit;
  
    //update memberID
    const memberRegistry = await getParticipantRegistry('org.pikachu2.biznet.Member');
    await memberRegistry.update(userloseCredit.member);
   
  }
  
  /**
 * companyEarnCredit transaction
 * @param {org.pikachu2.biznet.companyEarnCredit} companyearncredit
 * @transaction
 */
async function companyEarnCredit(companyearncredit) {

  //update company nowcredit
  companyearncredit.company.Info.Credit = companyearncredit.company.Info.Credit + companyearncredit.Credit;

  //update company
  const companyRegistry = await getParticipantRegistry('org.pikachu2.biznet.Company');
  await companyRegistry.update(companyearncredit.company);
}
 /**
 * companyloseCredit transaction
 * @param {org.pikachu2.biznet.companyLoseCredit} companyloseCredit
 * @transaction
 */
async function companyLoseCredit(companyloseCredit) {
    //update company Credit
    companyloseCredit.company.Info.Credit = companyloseCredit.company.Info.Credit - companyloseCredit.Credit;
  
    //update company
    const companyRegistry = await getParticipantRegistry('org.pikachu2.biznet.Company');
    await companyRegistry.update(companyloseCredit.company);
   
  }
/**
 * EarnPoints transaction
 * @param {org.pikachu2.biznet.userEarnPoints} userearnPoints
 * @transaction
 */
async function userEarnPoints(userearnPoints) {

  //update member points
  userearnPoints.member.Info.points = userearnPoints.member.Info.points + userearnPoints.points;

  //update member
  const memberRegistry = await getParticipantRegistry('org.pikachu2.biznet.Member');
  await memberRegistry.update(userearnPoints.member);

  // check if company exists on the network
  const companyRegistry = await getParticipantRegistry('org.pikachu2.biznet.Company');
  companyExists = await companyRegistry.exists(userearnPoints.company.id);
  if (companyExists == false) {
    throw new Error('company does not exist - check company id');
  }
}
/**
 * userUsePoints transaction
 * @param {org.pikachu2.biznet.userUsePoints} userusePoints
 * @transaction
 */
async function userUsePoints(userusePoints) {

  //check if member has sufficient points
  if (userusePoints.member.Info.points < userusePoints.Info.points) {
    throw new Error('Insufficient points');
  }

  //update member points
  userusePoints.member.Info.points = userusePoints.member.Info.points - userusePoints.points

  //update member
  const memberRegistry = await getParticipantRegistry('org.pikachu2.biznet.Member');
  await memberRegistry.update(userusePoints.member);

  // check if company exists on the network
  const companyRegistry = await getParticipantRegistry('org.pikachu2.biznet.Company');
  companyExists = await partnerRegistry.exists(userusePoints.company.id);
  if (companyExists == false) {
    throw new Error('company does not exist - check id');
  }
}
/**
 * EarnPoints transaction
 * @param {org.pikachu2.biznet.userEarnPoints} userearnPoints
 * @transaction
 */
async function companyEarnPoints(companyearnPoints) {

  //update company points
  companyearnPoints.company.Info.points = companyearnPoints.company.Info.points + companyearnPoints.Info.points;

  //update company
  const memberRegistry = await getParticipantRegistry('org.pikachu2.biznet.Company');
  await memberRegistry.update(companyearnPoints.company);
}

/**
 * companyUsePoints transaction
 * @param {org.pikachu2.biznet.companyUsePoints} companyusePoints
 * @transaction
 */
async function companyUsePoints(companyusePoints) {

  //check if company has sufficient points
  if (companyusePoints.member.Info.points < companyusePoints.Info.points) {
    throw new Error('Insufficient points');
  }

  //update company points
  companyusePoints.company.Info.points = companyusePoints.company.Info.points - companyusePoints.Info.points

  //update company
  const companyRegistry = await getParticipantRegistry('org.pikachu2.biznet.Company');
  await companyRegistry.update(companyusePoints.member);

}
