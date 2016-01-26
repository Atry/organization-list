package com.thoughtworks.microbuilder.tutorial.organizationList.controllers

import play.api.mvc.{Action, Controller}

import scala.concurrent.{ExecutionContext, Future}

import scalaz.std.scalaFuture._
import com.thoughtworks.each.Monadic._

import com.thoughtworks.microbuilder.play.Implicits._

import com.thoughtworks.microbuilder.tutorial.githubSdk.model.OrganizationSummary
import com.thoughtworks.microbuilder.tutorial.githubSdk.rpc.IOrganizationService
import com.thoughtworks.microbuilder.tutorial.organizationList.views.html.renderOrganizationList


class OrganizationListController(organizationService: IOrganizationService)(implicit ec: ExecutionContext) extends Controller {

  def showOrganizationList(username: String) = Action.async(throwableMonadic[Future] {
    val future: Future[Array[OrganizationSummary]] = organizationService.listUserOrganizations(username)
    Ok(renderOrganizationList(username, future.each))
  })

}