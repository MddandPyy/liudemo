package com.example.liudemo.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: ladeit
 * @description: User
 * @author: sora
 * @create: 2019/10/30
 * @version: 1.0.0
 */
@Entity
@Table(name="user")
@Data
public class User {

	/**
	 * 主键 primary key
	 */
	@Id
	private String id;

	/**
	 * 用户昵称
	 */
	@Column(name = "slack_userid")
	private String slackUserId;

	/**
	 * 姓
	 */
	@Column(name = "slack_username")
	private String slackUserName;

	/**
	 * 用户名
	 */
	@Column(name = "github_username")
	private String githubUserName;

	/**
	 * 用户密码
	 */
	@Column(name = "github_repos_url")
	private String githubReposUrl;

	/**
	 * 用户密码
	 */
	@Column(name = "github_token")
	private String githubToken;

}
