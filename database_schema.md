# Database Schema for Organization Service

This document outlines the database schema for the Organization Service. Each table is detailed with its columns, data types, and relationships.

## Core Entities

### `organizations`

Stores information about organizations.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the organization. |
| `name` | `VARCHAR(255)` | Not Null | The name of the organization. |
| `legal_name` | `VARCHAR(255)` | | The legal name of the organization. |
| `display_name` | `VARCHAR(255)` | | The display name of the organization. |
| `slug` | `VARCHAR(100)` | Not Null, Unique | A unique slug for the organization. |
| `business_type` | `VARCHAR(255)` | Not Null | Enum: `B2B`, `B2C`, `B2B2C`, `MARKETPLACE`. |
| `industry` | `VARCHAR(255)` | | The industry of the organization. |
| `company_size` | `VARCHAR(255)` | Not Null | Enum: `STARTUP`, `SMB`, `MID_MARKET`, `ENTERPRISE`. |
| `annual_revenue_range` | `VARCHAR(255)` | | The annual revenue range of the organization. |
| `registration_number` | `VARCHAR(255)` | | The registration number of the organization. |
| `tax_id` | `VARCHAR(255)` | | The tax ID of the organization. |
| `vat_number` | `VARCHAR(255)` | | The VAT number of the organization. |
| `incorporation_country` | `VARCHAR(255)` | | The incorporation country of the organization. |
| `incorporation_date` | `TIMESTAMP` | | The incorporation date of the organization. |
| `website_url` | `VARCHAR(255)` | | The website URL of the organization. |
| `support_email` | `VARCHAR(255)` | | The support email of the organization. |
| `billing_email` | `VARCHAR(255)` | | The billing email of the organization. |
| `phone` | `VARCHAR(255)` | | The phone number of the organization. |
| `primary_currency` | `VARCHAR(3)` | Not Null | The primary currency of the organization. |
| `timezone` | `VARCHAR(255)` | Not Null | The timezone of the organization. |
| `date_format` | `VARCHAR(255)` | | The date format used by the organization. |
| `number_format` | `VARCHAR(255)` | | The number format used by the organization. |
| `default_payment_terms` | `INTEGER` | | The default payment terms for the organization. |
| `billing_cycle` | `VARCHAR(255)` | | Enum: `MONTHLY`, `QUARTERLY`, `ANNUALLY`, `CUSTOM`. |
| `invoice_prefix` | `VARCHAR(255)` | | The prefix for invoices. |
| `invoice_counter_start` | `INTEGER` | | The starting number for invoice counters. |
| `onboarding_status` | `VARCHAR(255)` | | Enum: `PENDING`, `IN_PROGRESS`, `COMPLETED`, `SUSPENDED`. |
| `onboarding_step` | `INTEGER` | | The current onboarding step of the organization. |
| `subscription_tier` | `VARCHAR(255)` | | Enum: `STARTER`, `PROFESSIONAL`, `ENTERPRISE`, `CUSTOM`. |
| `gdpr_compliant` | `BOOLEAN` | | Whether the organization is GDPR compliant. |
| `encryption_required` | `BOOLEAN` | | Whether encryption is required for the organization. |
| `data_residency_preference` | `VARCHAR(255)` | | The data residency preference of the organization. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the organization was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the organization was last updated. |
| `created_by` | `UUID` | | The user who created the organization. |
| `status` | `VARCHAR(255)` | Not Null | Enum: `ACTIVE`, `INACTIVE`, `SUSPENDED`, `DELETED`. |

### `users`

Stores information about users.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the user. |
| `first_name` | `VARCHAR(100)` | Not Null | The first name of the user. |
| `last_name` | `VARCHAR(100)` | Not Null | The last name of the user. |
| `email` | `VARCHAR(255)` | Not Null, Unique | The email address of the user. |
| `phone` | `VARCHAR(255)` | | The phone number of the user. |
| `password_hash` | `VARCHAR(255)` | Not Null | The hashed password of the user. |
| `email_verified` | `BOOLEAN` | | Whether the user's email has been verified. |
| `email_verification_token` | `VARCHAR(255)` | | The token used for email verification. |
| `email_verified_at` | `TIMESTAMP` | | The timestamp when the email was verified. |
| `two_factor_enabled` | `BOOLEAN` | | Whether two-factor authentication is enabled. |
| `two_factor_secret` | `VARCHAR(255)` | | The secret key for two-factor authentication. |
| `last_login_at` | `TIMESTAMP` | | The timestamp of the user's last login. |
| `login_attempts` | `INTEGER` | | The number of failed login attempts. |
| `locked_until` | `TIMESTAMP` | | The timestamp until which the user is locked out. |
| `avatar_url` | `VARCHAR(255)` | | The URL of the user's avatar. |
| `job_title` | `VARCHAR(255)` | | The job title of the user. |
| `department` | `VARCHAR(255)` | | The department of the user. |
| `timezone` | `VARCHAR(255)` | | The timezone of the user. |
| `language` | `VARCHAR(255)` | | The language preference of the user. |
| `notification_preferences` | `JSONB` | | The notification preferences of the user. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the user was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the user was last updated. |
| `status` | `VARCHAR(255)` | Not Null | Enum: `ACTIVE`, `INACTIVE`, `SUSPENDED`, `DELETED`. |

### `roles`

Stores information about roles.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the role. |
| `organization_id` | `UUID` | Foreign Key to `organizations.id` | The organization to which the role belongs. |
| `name` | `VARCHAR(100)` | Not Null | The name of the role. |
| `description` | `VARCHAR(255)` | | A description of the role. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the role was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the role was last updated. |

### `permissions`

Stores information about permissions.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the permission. |
| `name` | `VARCHAR(150)` | Not Null, Unique | The name of the permission. |
| `description` | `VARCHAR(255)` | | A description of the permission. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the permission was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the permission was last updated. |

### `role_permissions`

Join table for `roles` and `permissions`.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `role_id` | `UUID` | Foreign Key to `roles.id` | The ID of the role. |
| `permission_id` | `UUID` | Foreign Key to `permissions.id` | The ID of the permission. |

### `organization_members`

Links users to organizations with specific roles.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the membership. |
| `organization_id` | `UUID` | Foreign Key to `organizations.id` | The ID of the organization. |
| `user_id` | `UUID` | Foreign Key to `users.id` | The ID of the user. |
| `role` | `VARCHAR(50)` | Not Null | Enum: `OWNER`, `ADMIN`, `BILLING_MANAGER`, `DEVELOPER`, `VIEWER`. |
| `permissions` | `JSONB` | | Granular permission overrides for the user. |
| `status` | `VARCHAR(255)` | Not Null | Enum: `ACTIVE`, `INVITED`, `SUSPENDED`. |
| `invited_at` | `TIMESTAMP` | | The timestamp when the user was invited. |
| `joined_at` | `TIMESTAMP` | | The timestamp when the user joined. |
| `invited_by` | `UUID` | Foreign Key to `users.id` | The user who invited this member. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the membership was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the membership was last updated. |

## Supporting Entities

### `organization_addresses`

Stores addresses for organizations.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the address. |
| `organization_id` | `UUID` | Foreign Key to `organizations.id` | The ID of the organization. |
| `type` | `VARCHAR(50)` | Not Null | Enum: `BILLING`, `HEADQUARTERS`, `SHIPPING`, `OTHER`. |
| `line1` | `VARCHAR(255)` | Not Null | The first line of the address. |
| `line2` | `VARCHAR(255)` | | The second line of the address. |
| `city` | `VARCHAR(255)` | | The city of the address. |
| `state` | `VARCHAR(255)` | | The state or province of the address. |
| `postal_code` | `VARCHAR(255)` | | The postal code of the address. |
| `country` | `VARCHAR(255)` | | The country of the address. |
| `latitude` | `DECIMAL(10, 6)` | | The latitude of the address. |
| `longitude` | `DECIMAL(10, 6)` | | The longitude of the address. |
| `active` | `BOOLEAN` | | Whether the address is active. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the address was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the address was last updated. |

### `organization_api_keys`

Manages API keys for organizations.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the API key. |
| `organization_id` | `UUID` | Foreign Key to `organizations.id` | The ID of the organization. |
| `name` | `VARCHAR(100)` | Not Null | The name of the API key. |
| `key_hash` | `VARCHAR(255)` | Not Null | The hashed API key. |
| `key_prefix` | `VARCHAR(16)` | Not Null, Unique | The prefix of the API key. |
| `scopes` | `JSONB` | | The scopes of the API key. |
| `expires_at` | `TIMESTAMP` | | The expiration date of the API key. |
| `status` | `VARCHAR(255)` | Not Null | Enum: `ACTIVE`, `REVOKED`. |
| `revoked_at` | `TIMESTAMP` | | The timestamp when the API key was revoked. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the API key was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the API key was last updated. |

### `organization_divisions`

Represents subdivisions within an organization.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the division. |
| `organization_id` | `UUID` | Foreign Key to `organizations.id` | The ID of the organization. |
| `name` | `VARCHAR(255)` | Not Null | The name of the division. |
| `description` | `VARCHAR(255)` | | A description of the division. |
| `email` | `VARCHAR(255)` | | The email address of the division. |
| `status` | `VARCHAR(255)` | Not Null | Enum: `ACTIVE`, `INACTIVE`, `SUSPENDED`. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the division was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the division was last updated. |

### `organization_onboarding_steps`

Tracks the onboarding progress of an organization.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the onboarding step. |
| `organization_id` | `UUID` | Foreign Key to `organizations.id` | The ID of the organization. |
| `step` | `VARCHAR(50)` | Not Null | Enum: `PROFILE`, `USERS`, `ADDRESS`, `BILLING`, `DONE`. |
| `status` | `VARCHAR(20)` | Not Null | Enum: `PENDING`, `COMPLETE`. |
| `completed_at` | `TIMESTAMP` | | The timestamp when the step was completed. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the step was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the step was last updated. |

### `organization_settings`

Stores settings for an organization.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the settings. |
| `organization_id` | `UUID` | Foreign Key to `organizations.id`, Unique | The ID of the organization. |
| `settings` | `JSONB` | Not Null | The settings for the organization. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the settings were created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the settings were last updated. |

### `user_settings`

Stores settings for a user.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the settings. |
| `user_id` | `UUID` | Foreign Key to `users.id`, Unique | The ID of the user. |
| `settings` | `JSONB` | Not Null | The settings for the user. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the settings were created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the settings were last updated. |

### `customers`

Represents customers of an organization or division.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the customer. |
| `name` | `VARCHAR(255)` | Not Null | The name of the customer. |
| `email` | `VARCHAR(255)` | Not Null | The email address of the customer. |
| `phone` | `VARCHAR(255)` | | The phone number of the customer. |
| `status` | `VARCHAR(255)` | Not Null | Enum: `ACTIVE`, `INACTIVE`, `SUSPENDED`. |
| `organization_id` | `UUID` | Foreign Key to `organizations.id` | The ID of the organization. |
| `division_id` | `UUID` | Foreign Key to `organization_divisions.id` | The ID of the division. |
| `start_date` | `TIMESTAMP` | Not Null | The start date of the customer relationship. |
| `metadata` | `JSONB` | | Additional metadata for the customer. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the customer was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the customer was last updated. |

### `onboarding_step_templates`

Templates for onboarding steps.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the template. |
| `name` | `VARCHAR(100)` | Not Null | The name of the template. |
| `description` | `VARCHAR(500)` | | A description of the template. |
| `step_order` | `INTEGER` | Not Null | The order of the step. |
| `config` | `TEXT` | | The JSON configuration for the step. |
| `active` | `BOOLEAN` | Not Null | Whether the template is active. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the template was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the template was last updated. |

### `rate_limit_rules`

Defines rate limiting rules for the API.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the rule. |
| `scope_type` | `VARCHAR(20)` | Not Null | The scope of the rule (e.g., `GLOBAL`, `ORGANIZATION`, `USER`). |
| `scope_id` | `UUID` | | The ID of the scope (e.g., `organization_id`, `user_id`). |
| `window_seconds` | `INTEGER` | Not Null | The time window in seconds. |
| `max_requests` | `INTEGER` | Not Null | The maximum number of requests allowed in the window. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the rule was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the rule was last updated. |

### `system_settings`

Stores system-wide settings.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the setting. |
| `setting_key` | `VARCHAR(100)` | Not Null, Unique | The key of the setting. |
| `value` | `TEXT` | Not Null | The value of the setting. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the setting was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the setting was last updated. |

### `api_call_logs`

Logs API calls.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the log entry. |
| `user_id` | `UUID` | Foreign Key to `users.id` | The ID of the user who made the call. |
| `organization_id` | `UUID` | Foreign Key to `organizations.id` | The ID of the organization. |
| `method` | `VARCHAR(10)` | Not Null | The HTTP method of the call. |
| `path` | `VARCHAR(500)` | Not Null | The path of the API call. |
| `status_code` | `INTEGER` | | The status code of the response. |
| `duration_ms` | `BIGINT` | | The duration of the call in milliseconds. |
| `ip_address` | `VARCHAR(50)` | | The IP address of the caller. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the call was made. |

### `audit_logs`

Logs audit events.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the log entry. |
| `actor_user_id` | `UUID` | Foreign Key to `users.id` | The ID of the user who performed the action. |
| `entity` | `VARCHAR(100)` | Not Null | The entity that was affected. |
| `entity_id` | `UUID` | | The ID of the entity that was affected. |
| `action` | `VARCHAR(20)` | Not Null | Enum: `CREATE`, `UPDATE`, `DELETE`. |
| `details` | `JSONB` | | The details of the change. |
| `ip_address` | `VARCHAR(50)` | | The IP address of the user. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the action was performed. |

### `outbox_events`

Used for the outbox pattern to ensure reliable event delivery.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the event. |
| `aggregate_type` | `VARCHAR(255)` | Not Null | The type of the aggregate. |
| `aggregate_id` | `UUID` | Not Null | The ID of the aggregate. |
| `event_type` | `VARCHAR(255)` | Not Null | The type of the event. |
| `payload` | `TEXT` | Not Null | The JSON payload of the event. |
| `status` | `VARCHAR(255)` | Not Null | Enum: `PENDING`, `SENT`. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the event was created. |
| `sent_at` | `TIMESTAMP` | | The timestamp when the event was sent. |

### `oauth_providers`

Stores configuration for OAuth providers.

| Column | Type | Constraints | Description |
| --- | --- | --- | --- |
| `id` | `UUID` | Primary Key | Unique identifier for the provider. |
| `name` | `VARCHAR(50)` | Not Null, Unique | The name of the provider (e.g., `GOOGLE`, `GITHUB`). |
| `client_id` | `VARCHAR(255)` | Not Null | The client ID of the provider. |
| `client_secret` | `VARCHAR(255)` | Not Null | The client secret of the provider. |
| `auth_url` | `VARCHAR(255)` | Not Null | The authorization URL of the provider. |
| `token_url` | `VARCHAR(255)` | Not Null | The token URL of the provider. |
| `scopes` | `VARCHAR(255)` | | The scopes required by the provider. |
| `active` | `BOOLEAN` | Not Null | Whether the provider is active. |
| `created_at` | `TIMESTAMP` | Not Null | The timestamp when the provider was created. |
| `updated_at` | `TIMESTAMP` | Not Null | The timestamp when the provider was last updated. |
