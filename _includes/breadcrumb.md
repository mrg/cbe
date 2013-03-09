{% if page.breadcrumb %}
  <ul class="breadcrumb breadcrumb-{% cycle 'top', 'bottom' %}">
    {% for crumb in page.breadcrumb %}
      <li>
        {% if crumb.link %}
          <a href="{{ crumb.link }}">{{ crumb.name }}</a>
        {% else %}
          {{ crumb.name }}
        {% endif %}
        {% if forloop.last != true %}
          &rArr;
        {% endif %}
      </li>
    {% endfor %}
  </ul>
{% endif %}

